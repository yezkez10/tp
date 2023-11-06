package seedu.address.model.timeslots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalTimeslots;

public class OnDateTimeSlotPredicateTest {
    private LocalDate defaultDate = TypicalTimeslots.DEFAULT_TIMESLOT.getDate();
    //Heuristics: Equivalence Partition : DATE can be null/same date/different date
    @Test
    public void createPredicateNullFailure() {
        assertThrows(NullPointerException.class, () -> new OnDateTimeSlotPredicate(null));
    }
    @Test
    public void createPredicateCorrectDateSuccess() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(defaultDate);
        assertEquals(predicate.getDate(), defaultDate);
    }

    @Test
    public void testNullFailure() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(defaultDate);
        assertThrows(NullPointerException.class, () -> predicate.test(null));
    }

    @Test
    public void testSameDateSuccess() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(defaultDate);
        assertTrue(predicate.test(TypicalTimeslots.DEFAULT_TIMESLOT));
    }

    @Test
    public void testDifferentDateFailure() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(defaultDate);
        assertFalse(predicate.test(TypicalTimeslots.TIMESLOT_TWO));
    }

    @Test
    public void equals() {
        // same values -> returns true
        OnDateTimeSlotPredicate predicateToTest = new OnDateTimeSlotPredicate(defaultDate);

        // same object -> returns true
        assertTrue(predicateToTest.equals(predicateToTest));

        // null -> returns false
        assertFalse(predicateToTest.equals(null));

        // different type -> returns false
        assertFalse(predicateToTest.equals(5));

        // different dates -> returns false
        assertFalse(predicateToTest.equals(new OnDateTimeSlotPredicate(TypicalTimeslots.TIMESLOT_FOUR.getDate())));

        //same date
        OnDateTimeSlotPredicate predicateToTestSameDate = new OnDateTimeSlotPredicate(defaultDate);
        assertTrue(predicateToTestSameDate.equals(predicateToTest));
    }

    @Test
    public void toStringMethod() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(defaultDate);
        String expected = OnDateTimeSlotPredicate.class.getCanonicalName()
                + "{date=" + predicate.getDate() + "}";
        assertEquals(expected, predicate.toString());
    }
}
