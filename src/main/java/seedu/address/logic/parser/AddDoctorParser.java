package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AddDoctorCommand object
 */
public class AddDoctorParser implements Parser<AddDoctorCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDoctorCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GENDER, PREFIX_AGE,
                         PREFIX_ADDRESS);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GENDER, PREFIX_AGE,
                PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_GENDER, PREFIX_AGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
        }

        // If any compulsory fields are empty
        if (argMultimap.getValue(PREFIX_NAME).isEmpty()
                || argMultimap.getValue(PREFIX_ADDRESS).isEmpty()
                || argMultimap.getValue(PREFIX_PHONE).isEmpty()
                || argMultimap.getValue(PREFIX_EMAIL).isEmpty()
                || argMultimap.getValue(PREFIX_GENDER).isEmpty()
                || argMultimap.getValue(PREFIX_AGE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
        }

        // If any compulsory fields are empty strings
        if (argMultimap.getValue(PREFIX_NAME).get().trim().isEmpty()
                || argMultimap.getValue(PREFIX_ADDRESS).get().trim().isEmpty()
                || argMultimap.getValue(PREFIX_PHONE).get().trim().isEmpty()
                || argMultimap.getValue(PREFIX_EMAIL).get().trim().isEmpty()
                || argMultimap.getValue(PREFIX_GENDER).get().trim().isEmpty()
                || argMultimap.getValue(PREFIX_AGE).get().trim().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDoctorCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Doctor doctor = new Doctor(name, phone, email, gender, age, address);

        return new AddDoctorCommand(doctor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
