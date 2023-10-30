package seedu.address.testutil;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalDoctors.getTypicalDoctors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * A utility class for test cases.
 */
public class TestUtil {

    /**
     * Folder used for temp files created during testing. Ignored by Git.
     */
    private static final Path SANDBOX_FOLDER = Paths.get("src", "test", "data", "sandbox");

    /**
     * Appends {@code fileName} to the sandbox folder path and returns the resulting path.
     * Creates the sandbox folder if it doesn't exist.
     */
    public static Path getFilePathInSandboxFolder(String fileName) {
        try {
            Files.createDirectories(SANDBOX_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SANDBOX_FOLDER.resolve(fileName);
    }

    /**
     * Returns the middle index of the person in the {@code model}'s person list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPersonList().size() / 2);
    }

    /**
     * Returns the last index of the person in the {@code model}'s person list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredPersonList().size());
    }

    /**
     * Returns the person in the {@code model}'s person list at {@code index}.
     */
    public static Person getPerson(Model model, Index index) {
        return model.getFilteredPersonList().get(index.getZeroBased());
    }
    public static ClinicAssistant getTypicalAddressBook() {
        ClinicAssistant ab = new ClinicAssistant();
        List<Person> appointmentList = getTypicalAppointments();
        List<Doctor> doctorList = getTypicalDoctors();
        for (int i = 0; i < appointmentList.size(); i++) {
            ab.addPerson(appointmentList.get(i));
            for (Appointment appt : appointmentList.get(i).getAppointments()) {
                ab.addAppointment(appt);
                for (Doctor doctor: doctorList) {
                    if (doctor.getName().equals(new Name(appt.getName()))) {
                        doctor.addAppointment(appt);
                    }
                }
            }
            for (Doctor doctor : doctorList) {
                ab.addDoctor(doctor);
            }
        }
        return ab;
    }
}
