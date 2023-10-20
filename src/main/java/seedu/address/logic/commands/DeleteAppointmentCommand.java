package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Deletes the appointment of an existing person in the address book.
 */
public class DeleteAppointmentCommand extends Command {
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Index: %2$s";

    public static final String COMMAND_WORD = "delete_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Appointment of the person identified "
            + "by the index number of the specified person. "
            + "the person is specified by their Index.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT + "3";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Deleted Appointment %1$s  of %2$s";

    private final Index index;
    private final Index index2;

    /**
     * @param index Index of the patient
     * @param index2 Index of the appointment
     */
    public DeleteAppointmentCommand(Index index, Index index2) {
        requireAllNonNull(index, index2);

        this.index = index;
        this.index2 = index2;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (index2.getZeroBased() >= personToEdit.getAppointments().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment deleted = personToEdit.deleteAppointment(index2.getZeroBased());

        model.deleteAppointment(index2.getZeroBased());

        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, deleted,
                Messages.format(personToEdit)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteAppointmentCommand)) {
            return false;
        }

        DeleteAppointmentCommand e = (DeleteAppointmentCommand) other;
        return index.equals(e.index)
                && index2.equals(e.index2);
    }
}

