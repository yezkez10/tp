package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppointmentAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class AddAppointmentParser implements Parser<AppointmentAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppointmentAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FOR, PREFIX_DESCRIPTION, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_FOR, PREFIX_DESCRIPTION, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppointmentAddCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_NRIC, PREFIX_ADDRESS);
        Index patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_FOR).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        LocalDateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE).get());
        return new AppointmentAddCommand(patientIndex, description, dateTime);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
