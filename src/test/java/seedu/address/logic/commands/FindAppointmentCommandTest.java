package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_FOUND_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_NO_APPOINTMENTS_FOUND_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalAppointments.ALICE_WITH_APPOINTMENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NameContainsKeywordsApptPredicate;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsApptPredicate firstPredicate =
                new NameContainsKeywordsApptPredicate(Collections.singletonList("first"));
        NameContainsKeywordsApptPredicate secondPredicate =
                new NameContainsKeywordsApptPredicate(Collections.singletonList("second"));

        FindAppointmentsCommand findFirstCommand = new FindAppointmentsCommand(firstPredicate);
        FindAppointmentsCommand findSecondCommand = new FindAppointmentsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentsCommand findFirstCommandCopy = new FindAppointmentsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = MESSAGE_NO_APPOINTMENTS_FOUND_OVERVIEW;
        NameContainsKeywordsApptPredicate predicate = preparePredicate(" ");
        FindAppointmentsCommand command = new FindAppointmentsCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }
    
    //
    //    @Test
    //    public void execute_multipleKeywords_multipleAppointmentsFound() {
    //        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_FOUND_OVERVIEW, 3);
    //        NameContainsKeywordsApptPredicate predicate = preparePredicate("CHRIS christ Alice BENson Carl jeff");
    //        FindAppointmentsCommand command = new FindAppointmentsCommand(predicate);
    //        expectedModel.updateFilteredAppointmentList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(ALICE_WITH_APPOINTMENT.firstAppointment(),
    //                BENSON_WITH_APPOINTMENT.firstAppointment(), CARL_WITH_APPOINTMENT.firstAppointment()),
    //                model.getFilteredAppointmentList());
    //    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void execute_nameAndDate_appointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_FOUND_OVERVIEW, 1);
        NameContainsKeywordsApptPredicate namePredicate = preparePredicate("Alice");
        OnDateTimeApptPredicate datePredicate = new OnDateTimeApptPredicate(
                ALICE_WITH_APPOINTMENT.firstAppointment().getDateTime().toLocalDate());

        Predicate<Appointment> nameAndDatePredicate = namePredicate.and(datePredicate);
        FindAppointmentsCommand command = new FindAppointmentsCommand(nameAndDatePredicate);
        expectedModel.updateFilteredAppointmentList(nameAndDatePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE_WITH_APPOINTMENT.firstAppointment()),
                model.getFilteredAppointmentList());
    }

    @Test
    public void execute_dateSearch_appointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_FOUND_OVERVIEW, 1);
        OnDateTimeApptPredicate datePredicate = new OnDateTimeApptPredicate(
                ALICE_WITH_APPOINTMENT.firstAppointment().getDateTime().toLocalDate());
        FindAppointmentsCommand command = new FindAppointmentsCommand(datePredicate);
        expectedModel.updateFilteredAppointmentList(datePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE_WITH_APPOINTMENT.firstAppointment()),
                model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsApptPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsApptPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
