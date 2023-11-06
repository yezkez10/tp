package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.timeslots.Timeslot;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalTimeslots {
    public static final Timeslot DEFAULT_TIMESLOT = new TimeslotBuilder()
            .withDate(TimeslotBuilder.DEFAULT_DATE)
            .withHour(TimeslotBuilder.DEFAULT_HOUR).build();
    public static final Timeslot TIMESLOT_ONE = new TimeslotBuilder()
            .withDate("02-01-2024")
            .withHour(14).build();
    public static final Timeslot TIMESLOT_TWO = new TimeslotBuilder()
            .withDate("31-01-2024")
            .withHour(1).build();
    public static final Timeslot TIMESLOT_THREE = new TimeslotBuilder()
            .withDate("05-02-2024")
            .withHour(17).build();
    public static final Timeslot TIMESLOT_FOUR = new TimeslotBuilder()
            .withDate("17-10-2024")
            .withHour(12).build();

    private TypicalTimeslots() {} // prevents instantiation

    public static ArrayList<Timeslot> getTypicalTimeslots() {
        return new ArrayList<>(Arrays.asList(DEFAULT_TIMESLOT, TIMESLOT_ONE, TIMESLOT_TWO,
                TIMESLOT_THREE, TIMESLOT_FOUR));
    }
}
