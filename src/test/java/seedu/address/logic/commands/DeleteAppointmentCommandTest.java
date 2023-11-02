package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
public class DeleteAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_deleteAppointmentSuccessful() throws Exception {
        Appointment validAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        Person validPerson = validAppointment.getPerson();
        LocalDateTime time1 = ParserUtil.parseDateTime("02-01-2024 12:00");
        String validDoctor = validAppointment.getName();

        Index index = Index.fromZeroBased(model.getFilteredAppointmentList().indexOf(validAppointment));

        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(index);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteAppointment(validAppointment);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
               validAppointment, Messages.format(validPerson));

        assertCommandSuccess(deleteAppointmentCommand, model, expectedMessage, expectedModel);
    }
}
