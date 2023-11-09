package seedu.address.model.timeslots;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.timeslots.exceptions.DuplicateTimeslotException;
import seedu.address.testutil.TypicalTimeslots;

/**
 * Contains unit and integration tests for {@code UniqueTimeslotList}.
 */
public class UniqueTimeslotListTest {
    @Test
    public void equal() {
        UniqueTimeslotList uniqueTimeslotList = new UniqueTimeslotList();
        //Heuristics: Equivalence partition: validUniqueTimeslotList/null/int/String
        //same object
        assertTrue(uniqueTimeslotList.equals(uniqueTimeslotList));

        //different types
        assertFalse(uniqueTimeslotList.equals(5));
        assertFalse(uniqueTimeslotList.equals(""));
        assertFalse(uniqueTimeslotList.equals(null));

        UniqueTimeslotList uniqueTimeslotListCopy = new UniqueTimeslotList();
        uniqueTimeslotList.add(TypicalTimeslots.DEFAULT_TIMESLOT);
        uniqueTimeslotList.add(TypicalTimeslots.TIMESLOT_ONE);
        uniqueTimeslotList.add(TypicalTimeslots.TIMESLOT_TWO);
        uniqueTimeslotListCopy.add(TypicalTimeslots.DEFAULT_TIMESLOT);
        uniqueTimeslotListCopy.add(TypicalTimeslots.TIMESLOT_ONE);
        uniqueTimeslotListCopy.add(TypicalTimeslots.TIMESLOT_TWO);

        assertTrue(uniqueTimeslotList.equals(uniqueTimeslotListCopy));
    }
    @Test
    public void setTimeslotsWithNullTimeslot_failure() {
        UniqueTimeslotList uniqueTimeslotList = new UniqueTimeslotList();
        assertThrows(NullPointerException.class, () -> uniqueTimeslotList.setTimeslots(null));
    }

    @Test
    public void setTimeslotsWithTimeslots_success() {
        UniqueTimeslotList uniqueTimeslotList = new UniqueTimeslotList();
        UniqueTimeslotList uniqueTimeslotListCopy = new UniqueTimeslotList();
        uniqueTimeslotListCopy.add(TypicalTimeslots.DEFAULT_TIMESLOT);
        uniqueTimeslotListCopy.add(TypicalTimeslots.TIMESLOT_ONE);
        uniqueTimeslotListCopy.add(TypicalTimeslots.TIMESLOT_TWO);
        uniqueTimeslotListCopy.add(TypicalTimeslots.TIMESLOT_THREE);
        uniqueTimeslotListCopy.add(TypicalTimeslots.TIMESLOT_FOUR);
        assert uniqueTimeslotList.size() == 0;
        uniqueTimeslotList.setTimeslots(uniqueTimeslotListCopy);
        assertTrue(uniqueTimeslotList.size() == uniqueTimeslotListCopy.size());
    }

    @Test
    public void setTimeslotsListWithNullTimeslot_failure() {
        UniqueTimeslotList uniqueTimeslotList = new UniqueTimeslotList();
        List<Timeslot> timeslotList = new ArrayList<>();
        timeslotList.add(null);
        assertThrows(NullPointerException.class, () -> uniqueTimeslotList.setTimeslotsList(timeslotList));
    }

    @Test
    public void setTimeslotsListWithDuplicateTimeslot_failure() {
        UniqueTimeslotList uniqueTimeslotList = new UniqueTimeslotList();
        List<Timeslot> timeslotList = new ArrayList<>();
        Timeslot timeslot = TypicalTimeslots.DEFAULT_TIMESLOT;
        timeslotList.add(timeslot);
        timeslotList.add(timeslot);
        assertThrows(DuplicateTimeslotException.class, () -> uniqueTimeslotList.setTimeslotsList(timeslotList));
    }

    @Test
    public void setTimeslotsList_success() {
        UniqueTimeslotList uniqueTimeslotList = new UniqueTimeslotList();
        List<Timeslot> timeslotList = new ArrayList<>();
        timeslotList.add(TypicalTimeslots.DEFAULT_TIMESLOT);
        timeslotList.add(TypicalTimeslots.TIMESLOT_ONE);
        timeslotList.add(TypicalTimeslots.TIMESLOT_TWO);
        timeslotList.add(TypicalTimeslots.TIMESLOT_THREE);
        timeslotList.add(TypicalTimeslots.TIMESLOT_FOUR);
        assertTrue(uniqueTimeslotList.size() == 0);
        uniqueTimeslotList.setTimeslotsList(timeslotList);
        assertTrue(uniqueTimeslotList.size() == timeslotList.size());
    }
}
