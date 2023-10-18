package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.Person.createClone;

import java.time.LocalDateTime;
import java.util.*;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;


/**
 * Edits the details of an existing person in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "edit_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the specified Appointment of the patient identified "
            + "by the index number used in the displayed patients list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_APPOINTMENT
            + "[" + PREFIX_DESCRIPTION + "]"
            + "[" + PREFIX_DATE + "]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "1 "
            + PREFIX_DATE + "01-01-2024 00:00";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Newly edited Appointment %1$s of %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided: "
            + "[" + PREFIX_DESCRIPTION + "]"
            + "[" + PREFIX_DATE + "]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "1 "
            + PREFIX_DATE + "01-01-2024 00:00";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists for the patient.";

    private final Index patientIndex;
    private final Index appointmentIndex;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param patientIndex of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditAppointmentCommand(Index patientIndex, Index apptIndex, EditAppointmentDescriptor editPersonDescriptor) {
        requireNonNull(patientIndex);
        requireNonNull(apptIndex);
        requireNonNull(editPersonDescriptor);

        this.patientIndex = patientIndex;
        this.appointmentIndex = apptIndex;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // get the person to edit
        Person personToEdit = lastShownList.get(patientIndex.getZeroBased());
        Person editedPerson = createClone(personToEdit);
        ArrayList<Appointment> appointmentList = editedPerson.getAppointments();

        if (appointmentIndex.getZeroBased() >= appointmentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        // get the appointment to edit from the list of appointments of Person
        Appointment appointmentToEdit = appointmentList.get(appointmentIndex.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        editedPerson.editAppointment(appointmentIndex.getZeroBased(), editedAppointment);

        if (personToEdit.hasAppointment(editedAppointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment apptToEdit, EditAppointmentDescriptor editApptDesc) {
        assert apptToEdit != null;

        String updatedDescription = editApptDesc.getDescription().orElse(apptToEdit.getDescription());
        LocalDateTime updatedDateTime = editApptDesc.getDateTime().orElse(apptToEdit.getDateTime());

        return new Appointment(updatedDescription, updatedDateTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        EditAppointmentCommand otherEditAppointmentCommand = (EditAppointmentCommand) other;
        return patientIndex.equals(otherEditAppointmentCommand.patientIndex)
                && editAppointmentDescriptor.equals(otherEditAppointmentCommand.editAppointmentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientIndex", patientIndex)
                .add("appointmentIndex", appointmentIndex)
                .add("editAppointmentDescriptor", editAppointmentDescriptor)
                .toString();
    }

    public static class EditAppointmentDescriptor {
        private String description;
        private LocalDateTime dateTime;

        public EditAppointmentDescriptor(){}

        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setDescription(toCopy.description);
            setDateTime(toCopy.dateTime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, dateTime);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentCommand.EditAppointmentDescriptor)) {
                return false;
            }
            EditAppointmentCommand.EditAppointmentDescriptor otherAppointmentDescriptor
                    = (EditAppointmentCommand.EditAppointmentDescriptor) other;
            return Objects.equals(description, otherAppointmentDescriptor.description)
                    && Objects.equals(dateTime, otherAppointmentDescriptor.dateTime);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("description", description)
                    .add("dateTime", dateTime)
                    .toString();
        }
    }
}
