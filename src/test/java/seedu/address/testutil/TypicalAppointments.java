package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.ClinicAssistant;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {

    public static final Appointment ALICES_APPOINTMENT = new AppointmentBuilder(ALICE)
            .withDescription("See the doctor")
            .withDateTme("01-01-2024 00:00").build();

    public static final Appointment BENSONS_APPOINTMENT = new AppointmentBuilder(BENSON)
            .withDescription("Urine Exam")
            .withDateTme("01-11-2023 00:00").build();

    public static final Appointment CARLS_APPOINTMENT = new AppointmentBuilder(CARL)
            .withDescription("x-ray appointment")
            .withDateTme("05-02-2024 00:00").build();
    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalAppointments() {} // prevents instantiation

    public static ClinicAssistant getTypicalAddressBook() {
        ClinicAssistant ab = new ClinicAssistant();
        for (Appointment appt : getTypicalAppointments()) {
            ab.addAppointment(appt);
        }
        return ab;
    }

    public static ArrayList<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(ALICES_APPOINTMENT, BENSONS_APPOINTMENT, CARLS_APPOINTMENT));
    }
}
