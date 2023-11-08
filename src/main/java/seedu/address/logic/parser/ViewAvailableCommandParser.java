package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_DATE_DOES_NOT_EXIST;
import static seedu.address.logic.Messages.MESSAGE_DATE_TOO_SHORT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.Messages.MESSAGE_PAST_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.ParserUtil.isPastDate;
import static seedu.address.logic.parser.ParserUtil.isValidDateOnCalendar;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.logic.commands.ViewAvailableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;
import seedu.address.model.timeslots.Timeslot;

/**
 * Parser class for ViewAvailableCommand
 */
public class ViewAvailableCommandParser implements Parser<ViewAvailableCommand> {
    /**
     * Parses the input from the user and returns the ViewAvailableCommand
     * @param args String input from user
     * @return An instance of ViewAvailableCommand
     * @throws ParseException when format of ViewAvailableCommand is wrongly used
     */
    public ViewAvailableCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        //verify no duplicate input date
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE);

        //verify required argument is present
        if (argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
        }

        Predicate<Timeslot> predicate = null;
        Predicate<Appointment> apptPredicate = null;
        LocalDate date = null;
        // Check if required arguments are present
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String dateStr = argMultimap.getValue(PREFIX_DATE).get().trim();

            //check input format
            parseHelper(dateStr);

            //passes as long as it is a valid date on calender
            //02-01-2024 18:00 will pass but will be caught later
            if (!isValidDateOnCalendar(dateStr)) {
                throw new ParseException(MESSAGE_DATE_DOES_NOT_EXIST + "\n" + "Please insert in the following format: "
                        + "\n" + ViewAvailableCommand.MESSAGE_USAGE);
            }

            try {
                date = ParserUtil.parseDate(dateStr);
            } catch (ParseException e) {
                throw new ParseException(e.getMessage() + "\n" + "Please insert in the following format: " + "\n"
                        + ViewAvailableCommand.MESSAGE_USAGE);
            }

            if (isPastDate(date)) {
                throw new ParseException(MESSAGE_PAST_DATE + "\n" + "Please insert in the following format: " + "\n"
                        + ViewAvailableCommand.MESSAGE_USAGE);
            }

            predicate = new OnDateTimeSlotPredicate(date);
            apptPredicate = new OnDateTimeApptPredicate(date);
        }

        return new ViewAvailableCommand(predicate, apptPredicate, date);
    }

    /**
     * Helper function for parse
     * @param dateStr String to check if they are invalid inputs
     * @throws ParseException Exception thrown if dateStr is an invalid input
     */
    private void parseHelper(String dateStr) throws ParseException {
        if (dateStr.isEmpty() || dateStr.equals("\n")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewAvailableCommand.MESSAGE_USAGE));
        }
        //check if dd/MM/yyyy or has any alphabet
        if (dateStr.contains("/") || dateStr.matches(".*[a-zA-Z]+.*")) {
            throw new ParseException(MESSAGE_INVALID_DATE + "\n" + "Please insert in the following format: " + "\n"
                    + ViewAvailableCommand.MESSAGE_USAGE);
        }

        if (dateStr.length() < 10) {
            throw new ParseException(MESSAGE_DATE_TOO_SHORT + "\n" + "Please insert in the following format: "
                    + "\n" + ViewAvailableCommand.MESSAGE_USAGE);
        }
    }
}
