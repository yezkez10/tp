package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTimeslots.DEFAULT_TIMESLOT;
import static seedu.address.testutil.TypicalTimeslots.TIMESLOT_TWO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;

/**
 * Contains unit tests for {@code ViewAvailableCommand}.
 */
public class ViewAvailableCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        LocalDate defaultDate = DEFAULT_TIMESLOT.getDate();
        OnDateTimeApptPredicate appointmentPredicate =
                new OnDateTimeApptPredicate(defaultDate);
        OnDateTimeSlotPredicate timeslotPredicate =
                new OnDateTimeSlotPredicate(defaultDate);

        LocalDate dateTwo = TIMESLOT_TWO.getDate();
        OnDateTimeApptPredicate appointmentPredicateTwo =
                new OnDateTimeApptPredicate(dateTwo);
        OnDateTimeSlotPredicate timeslotPredicateTwo =
                new OnDateTimeSlotPredicate(dateTwo);

        ViewAvailableCommand firstViewCommand = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, defaultDate);
        ViewAvailableCommand secondViewCommand = new ViewAvailableCommand(timeslotPredicateTwo,
                appointmentPredicateTwo, dateTwo);

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same values -> returns true
        ViewAvailableCommand firstViewCommandCopy = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, timeslotPredicate.getDate());
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

        // different person -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }

    @Test
    public void toStringMethod() {
        LocalDate defaultDate = DEFAULT_TIMESLOT.getDate();
        OnDateTimeApptPredicate appointmentPredicate =
                new OnDateTimeApptPredicate(defaultDate);
        OnDateTimeSlotPredicate timeslotPredicate =
                new OnDateTimeSlotPredicate(defaultDate);
        ViewAvailableCommand viewAvailableCommand = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, defaultDate);
        String expected = ViewAvailableCommand.class.getCanonicalName() + "{predicate=" + timeslotPredicate + "}";
        assertEquals(expected, viewAvailableCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code OnDateTimeSlotPredicate}.
     */
    private OnDateTimeSlotPredicate preparePredicate(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(userInput, formatter);
        return new OnDateTimeSlotPredicate(date);
    }

    private LocalDate prepareDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
