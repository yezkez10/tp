package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALICES_APPOINTMENT_EDITED;
import static seedu.address.testutil.TypicalAppointments.ALICES_APPOINTMENT_EDITED_ONLY_DESCRIPTION_SPECIFIED;
import static seedu.address.testutil.TypicalAppointments.getTypicalAddressBookPatientsAndAppointments;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class EditAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookPatientsAndAppointments(), new UserPrefs());
    @Test
    public void execute_editAppointmentSuccessful() {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(ALICES_APPOINTMENT_EDITED).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);
        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                ALICES_APPOINTMENT_EDITED, Messages.format(ALICES_APPOINTMENT_EDITED.getPatient()));
        Model expectedModel = new ModelManager(new ClinicAssistant(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), ALICES_APPOINTMENT_EDITED);
        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() throws CommandException {
        EditAppointmentDescriptor descriptor =
                new EditAppointmentDescriptorBuilder(ALICES_APPOINTMENT_EDITED_ONLY_DESCRIPTION_SPECIFIED).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);
        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                ALICES_APPOINTMENT_EDITED_ONLY_DESCRIPTION_SPECIFIED,
                Messages.format(ALICES_APPOINTMENT_EDITED_ONLY_DESCRIPTION_SPECIFIED.getPatient()));
        Model expectedModel = new ModelManager(new ClinicAssistant(model.getAddressBook()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0),
                ALICES_APPOINTMENT_EDITED_ONLY_DESCRIPTION_SPECIFIED);
        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_failure() throws ParseException {
        Person validPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime time1 = ParserUtil.parseDateTime("02-01-2024 12:00");
        Appointment initialAdd = new Appointment("one", time1, validPerson);
        validPerson.addAppointment(initialAdd);

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().build();

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);
        // testcase returns MESSAGE_DUPLICATE_APPOINTMENT as without proper input -> takes .orElse() (original input)
        // thus, tests with MESSAGE_DUPLICATE_APPOINTMENT.
        assertCommandFailure(editAppointmentCommand, model, editAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_duplicateAppointment_failure() throws ParseException {
        Person validPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        LocalDateTime time1 = ParserUtil.parseDateTime("02-01-2024 12:00");
        Appointment initialAdd = new Appointment("one", time1, validPerson);
        validPerson.addAppointment(initialAdd);

        // edited person has SAME description and date
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().withDescription("one")
                .withDateTime("02-01-2024 12:00").build();

        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, editAppointmentCommand.MESSAGE_DUPLICATE_APPOINTMENT);
    }

    @Test
    public void execute_invalidPatientIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDescription(VALID_APPOINTMENT_DESCRIPTION)
                .withDateTime(VALID_APPOINTMENT_DATE).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex,
                INDEX_FIRST_APPOINTMENT, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
