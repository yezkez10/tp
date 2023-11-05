package seedu.address.model.timeslots;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TimeslotBuilder;
import seedu.address.testutil.TypicalTimeslots;

public class TimeslotTest {

    @Test
    public void createTimeslot_NULL_DATE_FAILURE() {
        assertThrows(NullPointerException.class, () -> new Timeslot(null, 3));
    }

    @Test
    public void isSameTimeslot() {
        Timeslot timeslotToTest = TypicalTimeslots.DEFAULT_TIMESLOT;
        // same object -> returns true
        assertTrue(timeslotToTest.isSameTimeSlot(timeslotToTest));

        // null -> returns false
        assertFalse(timeslotToTest.isSameTimeSlot(null));

        // date and hours same -> returns true
        Timeslot TIMESLOT_TWO = new TimeslotBuilder()
                .withDate(timeslotToTest.getDate()).withHour(timeslotToTest.getHour()).build();
        assertTrue(timeslotToTest.isSameTimeSlot(TIMESLOT_TWO));

        // different date, same hour -> returns false
        Timeslot TIMESLOT_THREE = new TimeslotBuilder()
                .withDate(TypicalTimeslots.TIMESLOT_THREE.getDate()).withHour(timeslotToTest.getHour()).build();
        assertFalse(timeslotToTest.isSameTimeSlot(TIMESLOT_THREE));

        // same date different hour -> returns false
        Timeslot TIMESLOT_FOUR = new TimeslotBuilder()
                .withDate(timeslotToTest.getDate()).withHour(TypicalTimeslots.TIMESLOT_FOUR.getHour()).build();
        assertFalse(timeslotToTest.isSameTimeSlot(TIMESLOT_FOUR));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Timeslot timeslotToTest = TypicalTimeslots.DEFAULT_TIMESLOT;

        // same object -> returns true
        assertTrue(timeslotToTest.equals(timeslotToTest));

        // null -> returns false
        assertFalse(timeslotToTest.equals(null));

        // different type -> returns false
        assertFalse(timeslotToTest.equals(5));

        // different timeslots -> returns false
        assertFalse(timeslotToTest.equals(TypicalTimeslots.TIMESLOT_TWO));

        //same date different hour
        Timeslot sameDateDifferentHourTimeslot = new Timeslot(timeslotToTest.getDate(), 12);
        assertFalse(timeslotToTest.equals(sameDateDifferentHourTimeslot));

        //different date same hour
        Timeslot differentDateSameHourTimeslot = new TimeslotBuilder()
                .withDate("01-01-2024").withHour(timeslotToTest.getHour()).build();
        assertFalse(timeslotToTest.equals(differentDateSameHourTimeslot));

        //same date same hour
        Timeslot timeslotToTestCopy = new TimeslotBuilder(timeslotToTest).build();
        assertTrue(timeslotToTest.equals(timeslotToTestCopy));
    }

    @Test
    public void toStringMethod() {
        String expected = Timeslot.class.getCanonicalName() + "{date=" + TypicalTimeslots.DEFAULT_TIMESLOT.getDate() +
                ", hour=" + TypicalTimeslots.DEFAULT_TIMESLOT.getHour() + "}";
        assertEquals(expected, TypicalTimeslots.DEFAULT_TIMESLOT.toString());
    }
}
