package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NricContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose NRIC equals the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindByNricCommand extends Command {

    public static final String COMMAND_WORD = "find_nric";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose NRIC matches "
            + "the specified NRIC (case-insensitive)\n"
            + "Parameters: NRIC\n"
            + "Example: " + COMMAND_WORD + " T1234567E";

    private final NricContainsKeywordPredicate predicate;

    public FindByNricCommand(NricContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int numberOfPatientsFound = model.getFilteredPersonList().size();

        if (numberOfPatientsFound == 0) {
            return new CommandResult(Messages.MESSAGE_NO_PATIENT_FOUND_OVERVIEW);
        } else if (numberOfPatientsFound == 1) {
            return new CommandResult(Messages.MESSAGE_ONE_PATIENT_FOUND_OVERVIEW);
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_PATIENTS_FOUND_OVERVIEW, model.getFilteredPersonList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindByNricCommand)) {
            return false;
        }

        FindByNricCommand otherFindCommand = (FindByNricCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
