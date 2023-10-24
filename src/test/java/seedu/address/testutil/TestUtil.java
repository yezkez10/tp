package seedu.address.testutil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

import static seedu.address.testutil.TypicalAppointments.getTypicalAppointments;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

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

    private static ClinicAssistant ab = new ClinicAssistant();
    public static ClinicAssistant getTypicalAddressBook() {
        ClinicAssistant ab = new ClinicAssistant();
//        List<Person> personList = getTypicalPersons();
        List<Person> appointmentList = getTypicalAppointments();
        System.out.println("\nappointment list is " +appointmentList);
        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println("\nperson to add is " + appointmentList.get(i));
//            System.out.println("Adding to person: " + personList.get(i));
//            Person toAdd = personList.get(i);

//            ab.addPerson(personList.get(i));
            ab.addPerson(appointmentList.get(i));
            for (Appointment appt : appointmentList.get(i).getAppointments()) {
                ab.addAppointment(appt);
            }
//            personList.get(i).addAppointment(appointmentList.get(i));
//            System.out.println("person added is " + personList.get(i));
        }
        return ab;
    }
}
