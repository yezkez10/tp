package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.NameContainsKeywordsApptPredicate;

/**
 * Finds and lists all Appointments in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindAppointmentsByNameCommand extends Command {

    public static final String COMMAND_WORD = "find_appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all Appointments of patient whose names contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsApptPredicate predicate;

    public FindAppointmentsByNameCommand(NameContainsKeywordsApptPredicate predicate) {
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
        if (!(other instanceof FindAppointmentsByNameCommand)) {
            return false;
        }

        FindAppointmentsByNameCommand otherFindCommand = (FindAppointmentsByNameCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
