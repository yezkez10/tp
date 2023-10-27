package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindAppointmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NameContainsKeywordsApptPredicate;
import seedu.address.model.appointment.OnDateTimeApptPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindAppointmentsCommandParser implements Parser<FindAppointmentsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentsCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE);

        // Check if required arguments are present
        if (argMultimap.getValue(PREFIX_NAME).isEmpty() && argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindAppointmentsCommand.MESSAGE_USAGE));
        }

        // Check if there are any duplicate prefixes
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATE);

        Predicate<Appointment> predicate = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String namesStr = argMultimap.getValue(PREFIX_NAME).get().trim();

            if (namesStr.isEmpty() || namesStr.equals("/on")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindAppointmentsCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = namesStr.split("\\s+");
            predicate = new NameContainsKeywordsApptPredicate(Arrays.asList(nameKeywords));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String dateStr = argMultimap.getValue(PREFIX_DATE).get().trim();

            if (dateStr.isEmpty() || dateStr.equals("/n")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        FindAppointmentsCommand.MESSAGE_USAGE));
            }

            LocalDate date = ParserUtil.parseDate(dateStr);

            if (predicate == null) {
                predicate = new OnDateTimeApptPredicate(date);
            } else {
                predicate = predicate.and(new OnDateTimeApptPredicate(date));
            }
        }

        return new FindAppointmentsCommand(predicate);
    }
}
