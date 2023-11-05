package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.person.Person;
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
            .withHour(15).build();
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

//    public static ArrayList<Person> getTypicalAppointments() {
//        return new ArrayList<>(Arrays.asList(ALICE_WITH_APPOINTMENT, BENSON_WITH_APPOINTMENT, CARL_WITH_APPOINTMENT,
//                DANIEL_WITH_APPOINTMENT, ELLE_WITH_APPOINTMENT, FIONA_WITH_APPOINTMENT, GEORGE_WITH_APPOINTMENT));
//    }
}
