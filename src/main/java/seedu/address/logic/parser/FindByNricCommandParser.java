package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindByNricCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NricContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindByNricCommand object
 */
public class FindByNricCommandParser implements Parser<FindByNricCommand> {

    /**
     * Parses the given {@code String} argument in the context of the FindByNricCommand
     * and returns a FindByNricCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindByNricCommand parse(String args) throws ParseException {
        String trimmedArg = args.trim();
        if (trimmedArg.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByNricCommand.MESSAGE_USAGE));
        }

        return new FindByNricCommand(new NricContainsKeywordPredicate(trimmedArg));
    }
}
