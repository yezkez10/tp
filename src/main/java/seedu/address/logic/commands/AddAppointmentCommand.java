package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOR;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.timeslots.Timeslot;

/**
 * A AppointmentAddCommand instance to add appointment to patients
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a appointment to the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "DESCRIPTION, DATE_TIME (must be a valid date in the future)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FOR + "1 "
            + PREFIX_DOC + "1 "
            + PREFIX_DESCRIPTION + "description details "
            + PREFIX_DATE + "02-01-2024 12:00";

    public static final String MESSAGE_SAME_APPOINTMENT_TIME = "This patient already has "
            + "an appointment at the same time.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists for the patient.";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT_DOCTOR = "This doctor already has "
            + "an appointment at the same time.";
    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "New appointment added |%1$s";
    public static final String MESSAGE_TIMESLOT_TAKEN = "This timeslot is already taken by %1$s";

    private final Index targetIndex;
    private final String description;
    private final LocalDateTime dateTime;

    private final Index doctorIndex;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Index targetIndex, Index doctorIndex, String description, LocalDateTime dateTime) {
        this.targetIndex = targetIndex;
        this.description = description;
        this.dateTime = dateTime;
        this.doctorIndex = doctorIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Doctor> lastDoctorList = model.getFilteredDoctorList();

        if (lastDoctorList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (doctorIndex.getZeroBased() > lastDoctorList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPatient = lastShownList.get(targetIndex.getZeroBased());
        Doctor targetDoctor = lastDoctorList.get(doctorIndex.getZeroBased());
        String name = targetDoctor.getName().toString();
        Appointment toAdd = new Appointment(description, dateTime, targetPatient, name);

        if (targetPatient.hasAppointmentOnTimeslot(toAdd)) {
            throw new CommandException(MESSAGE_SAME_APPOINTMENT_TIME);
        }
        if (targetPatient.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }
        if (targetDoctor.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_DOCTOR);
        }
        for (Doctor doctor : lastDoctorList) {
            if (doctor.equals(targetDoctor)) {
                // skip if current doctor is target doctor
                continue;
            }
            // checks if a doctor has an appointment on that timeslot already
            if (doctor.hasAppointmentOnTimeslot(dateTime)) {
                throw new CommandException(String.format(MESSAGE_TIMESLOT_TAKEN, doctor.getName()));
            }
        }

        targetPatient.addAppointment(toAdd);

        targetDoctor.addAppointment(toAdd);

        model.addAppointment(toAdd);
        Timeslot timeslotToRemove = new Timeslot(toAdd.getDateTime().toLocalDate(), toAdd.getDateTime().getHour());
        model.removeAvailableTimeSlot(timeslotToRemove);
        return new CommandResult(String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS, Messages.formatAppointment(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand otherAddCommand = (AddAppointmentCommand) other;
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
