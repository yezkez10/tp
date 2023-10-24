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

import seedu.address.model.ClinicAssistant;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Person ALICES_APPOINTMENT = new AppointmentBuilder(ALICE)
            .withDescription("See the doctor")
            .withDateTme("01-01-2024 00:00").build();
    public static final Person BENSONS_APPOINTMENT = new AppointmentBuilder(BENSON)
            .withDescription("Urine Exam")
            .withDateTme("01-11-2023 00:00").build();

    public static final Person CARLS_APPOINTMENT = new AppointmentBuilder(CARL)
            .withDescription("x-ray appointment")
            .withDateTme("05-02-2024 00:00").build();
    public static final Person DANIELS_APPOINTMENT = new AppointmentBuilder(DANIEL)
            .withDescription("z-ray appointment")
            .withDateTme("05-02-2024 00:00").build();

    public static final Person ELLES_APPOINTMENT = new AppointmentBuilder(ELLE)
            .withDescription("y-ray appointment")
            .withDateTme("05-02-2024 00:00").build();

    public static final Person FIONAS_APPOINTMENT = new AppointmentBuilder(FIONA)
            .withDescription("a-ray appointment")
            .withDateTme("05-02-2024 00:00").build();

    public static final Person GEORGES_APPOINTMENT = new AppointmentBuilder(GEORGE)
            .withDescription("x-ray appointment")
            .withDateTme("05-02-2024 00:00").build();

    private TypicalAppointments() {} // prevents instantiation

//    public static ClinicAssistant getTypicalAddressBook() {
//        ClinicAssistant ab = new ClinicAssistant();
//        for (Appointment appt : getTypicalAppointments()) {
//            ab.addAppointment(appt);
//        }
//        return ab;
//    }

    public static ArrayList<Person> getTypicalAppointments() {
        System.out.println("GET TYPICAL APPOINTMENT: " + ALICES_APPOINTMENT);
        System.out.println(new ArrayList<>(Arrays.asList(ALICES_APPOINTMENT, BENSONS_APPOINTMENT, CARLS_APPOINTMENT,
                DANIELS_APPOINTMENT, ELLES_APPOINTMENT, FIONAS_APPOINTMENT, GEORGES_APPOINTMENT)));
        return new ArrayList<>(Arrays.asList(ALICES_APPOINTMENT, BENSONS_APPOINTMENT, CARLS_APPOINTMENT,
                DANIELS_APPOINTMENT, ELLES_APPOINTMENT, FIONAS_APPOINTMENT, GEORGES_APPOINTMENT));
    }
}
