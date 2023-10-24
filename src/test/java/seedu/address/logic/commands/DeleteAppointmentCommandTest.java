package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.BaseTest;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class DeleteAppointmentCommandTest extends BaseTest {
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_deleteAppointmentSuccessful() throws Exception {
//        Person validPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
//        LocalDateTime time1 = ParserUtil.parseDateTime("02-01-2024 12:00");
//        LocalDateTime time2 = ParserUtil.parseDateTime("02-01-2025 12:00");
//        LocalDateTime time3 = ParserUtil.parseDateTime("02-01-2026 12:00");
//        LocalDateTime time4 = ParserUtil.parseDateTime("02-01-2027 12:00");
//        Appointment one = new Appointment("one", time1, validPerson);
//        Appointment two = new Appointment("two", time2, validPerson);
//        Appointment three = new Appointment("three", time3, validPerson);
//        Appointment four = new Appointment("4", time4, validPerson);
//
//        validPerson.addAppointment(one);
//        validPerson.addAppointment(two);
//        validPerson.addAppointment(three);
//        validPerson.addAppointment(four);
//        model.addAppointment(one);
//        model.addAppointment(two);
//        model.addAppointment(three);
//        model.addAppointment(four);
//
//        Index index = Index.fromZeroBased(model.getFilteredAppointmentList().indexOf(three));
//
//        DeleteAppointmentCommand command = new DeleteAppointmentCommand(index);
//        String expectedString = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, three,
//                Messages.format(validPerson));
//        assertEquals(new CommandResult(expectedString), command.execute(model));
    }
}
