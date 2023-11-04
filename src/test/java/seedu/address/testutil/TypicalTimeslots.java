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

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalTimeslots {
    public static final Person FIONA_WITH_APPOINTMENT = new AppointmentBuilder(FIONA)
            .withDescription("a-ray appointment")
            .withDateTme("05-02-2024 00:00").build();

    public static final Person GEORGE_WITH_APPOINTMENT = new AppointmentBuilder(GEORGE)
            .withDescription("x-ray appointment")
            .withDateTme("05-02-2024 00:00").build();

    private TypicalTimeslots() {} // prevents instantiation

//    public static ArrayList<Person> getTypicalAppointments() {
//        return new ArrayList<>(Arrays.asList(ALICE_WITH_APPOINTMENT, BENSON_WITH_APPOINTMENT, CARL_WITH_APPOINTMENT,
//                DANIEL_WITH_APPOINTMENT, ELLE_WITH_APPOINTMENT, FIONA_WITH_APPOINTMENT, GEORGE_WITH_APPOINTMENT));
//    }
}
