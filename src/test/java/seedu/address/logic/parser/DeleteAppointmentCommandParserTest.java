package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAppointmentCommand;

public class DeleteAppointmentCommandParserTest {
    private DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteAppointmentCommand() {
        assertParseSuccess(parser, "1" + " " + PREFIX_APPOINTMENT + "1",
                new DeleteAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON));
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a" + " " + PREFIX_APPOINTMENT + "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1" + " " + PREFIX_APPOINTMENT + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "" + " " + PREFIX_APPOINTMENT + "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAppointmentCommand.MESSAGE_USAGE));
    }
}
