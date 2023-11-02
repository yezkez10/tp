package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ETHNIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AppointmentAddCommand} objectg
 */
public class AddAppointmentParser implements Parser<AddAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FOR, PREFIX_DOC, PREFIX_DESCRIPTION, PREFIX_DATE);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_GENDER, PREFIX_AGE, PREFIX_ETHNIC, PREFIX_NRIC, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_FOR, PREFIX_DOC, PREFIX_DESCRIPTION, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }
        Index patientIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_FOR).get());
        Index doctorIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DOC).get());
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        LocalDateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATE).get());
        return new AddAppointmentCommand(patientIndex, doctorIndex, description, dateTime);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
