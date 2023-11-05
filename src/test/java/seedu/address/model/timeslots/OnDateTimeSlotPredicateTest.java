package seedu.address.model.timeslots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalTimeslots;

import java.time.LocalDate;

public class OnDateTimeSlotPredicateTest {
    private LocalDate DEFAULT_DATE = TypicalTimeslots.DEFAULT_TIMESLOT.getDate();

    @Test
    public void createPredicate_NULL_DATE_FAILURE() {
        assertThrows(NullPointerException.class, () -> new OnDateTimeSlotPredicate(null));
    }
    @Test
    public void createPredicate_CORRECT_DATE_SUCCESS() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        assertEquals(predicate.getDate(), DEFAULT_DATE);
    }

    @Test
    public void test_NULL_Failure() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        assertThrows(NullPointerException.class, () -> predicate.test(null));
    }

    @Test
    public void testSameDate_Success() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        assertTrue(predicate.test(TypicalTimeslots.DEFAULT_TIMESLOT));
    }

    @Test
    public void testdifferentDate_Failure() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        assertFalse(predicate.test(TypicalTimeslots.TIMESLOT_TWO));
    }

    @Test
    public void equals() {
        // same values -> returns true
        OnDateTimeSlotPredicate predicateToTest = new OnDateTimeSlotPredicate(DEFAULT_DATE);

        // same object -> returns true
        assertTrue(predicateToTest.equals(predicateToTest));

        // null -> returns false
        assertFalse(predicateToTest.equals(null));

        // different type -> returns false
        assertFalse(predicateToTest.equals(5));

        // different dates -> returns false
        assertFalse(predicateToTest.equals(new OnDateTimeSlotPredicate(TypicalTimeslots.TIMESLOT_FOUR.getDate())));

        //same date
        OnDateTimeSlotPredicate predicateToTestSameDate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        assertTrue(predicateToTestSameDate.equals(predicateToTest));
    }

    @Test
    public void toStringMethod() {
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(DEFAULT_DATE);
        String expected = OnDateTimeSlotPredicate.class.getCanonicalName() +
                "{date=" + predicate.getDate() + "}";
        assertEquals(expected, predicate.toString());
    }
}
