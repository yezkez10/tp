package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ViewAvailableCommand;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;
import seedu.address.testutil.TimeslotBuilder;

import java.time.LocalDate;

import static seedu.address.logic.Messages.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTimeslots.DEFAULT_TIMESLOT;

public class ViewAvailableCommandParserTest {

    private ViewAvailableCommandParser parser = new ViewAvailableCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyString_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyDate_throwsParseException() {
        assertParseFailure(parser, "view /on ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongArg_throwsParseException() { //wrong date separators
        assertParseFailure(parser, "view /on 20/12/2024",
                MESSAGE_INVALID_DATE + "\n" + "Please insert in the following format: " + "\n"
                + ViewAvailableCommand.MESSAGE_USAGE);
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
        assertParseFailure(parser, "view /on twenty december 20",
                MESSAGE_INVALID_DATE + "\n" + "Please insert in the following format: " + "\n"
                + ViewAvailableCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() { //wrong date format
        assertParseFailure(parser, "view /on 20th Dec",
                MESSAGE_INVALID_DATE + "\n" + "Please insert in the following format: " + "\n"
                + ViewAvailableCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_shortenedYear_throwsParseException() { //alphabet
        assertParseFailure(parser, "view /on 20-12-23",
                MESSAGE_DATE_TOO_SHORT + "\n" + "Please insert in the following format: " + "\n"
                + ViewAvailableCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_validArgs_returnsViewAvailableCommand() {
        LocalDate DEFAULT_DATE = DEFAULT_TIMESLOT.getDate();
        OnDateTimeSlotPredicate timeslotPredicate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        OnDateTimeApptPredicate apptPredicate = new OnDateTimeApptPredicate(DEFAULT_DATE);

        ViewAvailableCommand expectedViewAvailableCommand =
                  new ViewAvailableCommand(timeslotPredicate, apptPredicate, DEFAULT_DATE);

        // no whitespaces in between
        assertParseSuccess(parser, "view /on " + TimeslotBuilder.DEFAULT_DATE, expectedViewAvailableCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "view     /on    " + TimeslotBuilder.DEFAULT_DATE,
                expectedViewAvailableCommand);
    }
}

