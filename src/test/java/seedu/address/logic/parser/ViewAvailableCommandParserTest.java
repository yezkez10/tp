package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_DATE_DOES_NOT_EXIST;
import static seedu.address.logic.Messages.MESSAGE_DATE_TOO_SHORT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_DATE;
import static seedu.address.logic.Messages.MESSAGE_PAST_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTimeslots.DEFAULT_TIMESLOT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewAvailableCommand;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;
import seedu.address.testutil.TimeslotBuilder;

public class ViewAvailableCommandParserTest {

    private ViewAvailableCommandParser parser = new ViewAvailableCommandParser();

    //Heuristic: Equivalence partitioning of String = whitespaces/empty/invalid/alphabet/numbers/mix inputs
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", //whitespaces
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "", //empty
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyDate_throwsParseException() {
        assertParseFailure(parser, "view /on ", //invalid
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongArg_throwsParseException() { //wrong date separators
        assertParseFailure(parser, "view /on 20/12/2024", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidDateSeparator_throwsParseException() { //invalid date separator
        assertParseFailure(parser, "view /on 30-02-2024", MESSAGE_DATE_DOES_NOT_EXIST);
    }

    @Test
    public void parse_invalidMonth_throwsParseException() { //invalid month
        assertParseFailure(parser, "view /on 02-13-2024", MESSAGE_DATE_DOES_NOT_EXIST);
    }

    @Test
    public void parse_alphabet_throwsParseException() { //alphabet
        assertParseFailure(parser, "view /on twenty december 20", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() { //wrong date format
        assertParseFailure(parser, "view /on 20th Dec", MESSAGE_INVALID_DATE);
    }

    @Test
    public void parse_shortenedYear_throwsParseException() { //alphabet
        assertParseFailure(parser, "view /on 20-12-24", MESSAGE_DATE_TOO_SHORT);
    }

    @Test
    public void parse_withDateTime_throwsParseException() { //dateTime
        assertParseFailure(parser, "view /on 02-02-2024 18:00",
                MESSAGE_INVALID_DATE + "\n" + "Please insert in the following format: " + "\n"
                        + ViewAvailableCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_withPastDate_throwsParseException() { //past date
        assertParseFailure(parser, "view /on 02-02-2023", MESSAGE_PAST_DATE);
    }

    @Test
    public void parse_withYesterday_throwsParseException() { //Boundary Value Analysis: Yesterday
        LocalDate yesterdayDate = LocalDate.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateStr = yesterdayDate.format(formatter);
        assertParseFailure(parser, "view /on " + dateStr, MESSAGE_PAST_DATE);
    }

    @Test
    public void parse_withToday_success() { //BVA: same date as today
        LocalDate currDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateStr = currDate.format(formatter);
        OnDateTimeSlotPredicate timeslotPredicate = new OnDateTimeSlotPredicate(currDate);
        OnDateTimeApptPredicate apptPredicate = new OnDateTimeApptPredicate(currDate);
        ViewAvailableCommand expectedViewAvailableCommand =
                new ViewAvailableCommand(timeslotPredicate, apptPredicate, currDate);
        assertParseSuccess(parser, "view /on " + dateStr,
                expectedViewAvailableCommand);
    }

    @Test
    public void parse_validArgs_returnsViewAvailableCommand() {
        LocalDate defaultDate = DEFAULT_TIMESLOT.getDate();
        OnDateTimeSlotPredicate timeslotPredicate = new OnDateTimeSlotPredicate(defaultDate);
        OnDateTimeApptPredicate apptPredicate = new OnDateTimeApptPredicate(defaultDate);

        ViewAvailableCommand expectedViewAvailableCommand =
                  new ViewAvailableCommand(timeslotPredicate, apptPredicate, defaultDate);

        // no whitespaces in between
        assertParseSuccess(parser, "view /on " + TimeslotBuilder.DEFAULT_DATE, expectedViewAvailableCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "view     /on    " + TimeslotBuilder.DEFAULT_DATE,
                expectedViewAvailableCommand);
    }
}

