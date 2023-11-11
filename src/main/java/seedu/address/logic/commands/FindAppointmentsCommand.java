package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Finds and lists all Appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "find_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments"
            + " with patient name by keywords (case-insensitive) or date and displays them as a list with index "
            + "numbers.\n"
            + "Parameters: ["
            + PREFIX_NAME
            + "KEYWORD [MORE_KEYWORDS]...]"
            + "["
            + PREFIX_DATE
            + "DATE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie " + PREFIX_DATE + "01-01-2024";

    private final Predicate<Appointment> predicate;

    public FindAppointmentsCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(predicate);
        int numberOfAppointmentsFound = model.getFilteredAppointmentList().size();

        if (numberOfAppointmentsFound == 0) {
            return new CommandResult(Messages.MESSAGE_NO_APPOINTMENTS_FOUND_OVERVIEW);
        } else if (numberOfAppointmentsFound == 1) {
            return new CommandResult(Messages.MESSAGE_ONE_APPOINTMENT_FOUND_OVERVIEW);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_APPOINTMENTS_FOUND_OVERVIEW,
                            model.getFilteredAppointmentList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAppointmentsCommand)) {
            return false;
        }

        FindAppointmentsCommand otherFindCommand = (FindAppointmentsCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
