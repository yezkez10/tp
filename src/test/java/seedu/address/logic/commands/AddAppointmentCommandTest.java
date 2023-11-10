package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDoctors.BENSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DOCTOR;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyClinicAssistant;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.timeslots.Timeslot;

public class AddAppointmentCommandTest {

    @Test
    public void constructor_validAppointment_success() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(INDEX_FIRST_PERSON, INDEX_FIRST_DOCTOR,
                "Health check up", LocalDateTime.of(2024, 1, 1, 12, 0));
        assert(addAppointmentCommand != null);
    }

    @Test
    public void constructor_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null,
                null, null, null));
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(null,
                INDEX_FIRST_DOCTOR, "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0)));
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON,
                null, "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0)));
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR, null,
                LocalDateTime.of(2024, 1, 1, 12, 0)));
        assertThrows(NullPointerException.class, () -> new AddAppointmentCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR, "Health check up",
                null));
    }

    @Test
    public void execute_appointmentIndexZero_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, ()
                -> new AddAppointmentCommand(Index.fromOneBased(0), INDEX_FIRST_DOCTOR,
                "Health check up", LocalDateTime.of(2024, 1, 1, 12, 0)));
    }

    @Test
    public void execute_appointmentIndexOutOfBounds_throwsCommandException() {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Index.fromOneBased(3),
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        assertThrows(CommandException.class, ()
                -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void execute_appointmentIndexLessThanZero_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, ()
                -> new AddAppointmentCommand(Index.fromOneBased(-5),
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0)));
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() {
        ModelStubAcceptingAppointmentAdded modelStub = new ModelStubAcceptingAppointmentAdded();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(Index.fromOneBased(3),
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        try {
            addAppointmentCommand.execute(modelStub);
            assertThrows(CommandException.class, ()
                -> addAppointmentCommand.execute(modelStub));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void equals() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        AddAppointmentCommand addAppointmentCommandCopy = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        AddAppointmentCommand addAppointmentCommandDifferentIndex = new AddAppointmentCommand(
                Index.fromOneBased(2),
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        AddAppointmentCommand addAppointmentCommandDifferentDescription = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Blood test",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        AddAppointmentCommand addAppointmentCommandDifferentDateTime = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2025, 1, 1, 12, 0));
        AddAppointmentCommand addAppointmentCommandDifferentDateTime2 = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 2, 1, 12, 0));
        AddAppointmentCommand addAppointmentCommandDifferentDateTime3 = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 2, 12, 0));
        AddAppointmentCommand addAppointmentCommandDifferentDateTime4 = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 13, 10));

        assert(addAppointmentCommand.equals(addAppointmentCommandCopy));
        assert(!addAppointmentCommand.equals(addAppointmentCommandDifferentIndex));
        assert(!addAppointmentCommand.equals(addAppointmentCommandDifferentDescription));
        assert(!addAppointmentCommand.equals(addAppointmentCommandDifferentDateTime));
        assert(!addAppointmentCommand.equals(addAppointmentCommandDifferentDateTime2));
        assert(!addAppointmentCommand.equals(addAppointmentCommandDifferentDateTime3));
        assert(!addAppointmentCommand.equals(addAppointmentCommandDifferentDateTime4));
    }

    @Test
    public void toStringMethod() {
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(
                INDEX_FIRST_PERSON,
                INDEX_FIRST_DOCTOR,
                "Health check up",
                LocalDateTime.of(2024, 1, 1, 12, 0));
        String expected = AddAppointmentCommand.class.getCanonicalName() + "{targetIndex=" + INDEX_FIRST_PERSON
                + ", description=Health check up, dateTime=2024-01-01T12:00}";
        assertEquals(expected, addAppointmentCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyClinicAssistant newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClinicAssistant getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editedPersonAppointments(ArrayList<Appointment> oldAppointments, ArrayList<Appointment> toReplace) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDoctorList(Predicate<Doctor> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDoctor(Doctor toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDoctor(Doctor appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDoctor(Doctor target, Doctor editedDoctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDoctor(Doctor editedDoctor) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAvailableTimeSlot(Timeslot timeslot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeAvailableTimeSlot(Timeslot timeslot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetAvailableTimeSlot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAvailableTimeslot(Predicate<Timeslot> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Timeslot> getAvailableTimeSlotList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept the appointment being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends AddAppointmentCommandTest.ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointment);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyClinicAssistant getAddressBook() {
            return new ClinicAssistant();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            ClinicAssistant clinicAssistant = new ClinicAssistant();
            List<Person> personList = new ArrayList<>();
            personList.add(ALICE);
            clinicAssistant.setPersons(personList);
            return clinicAssistant.getPersonList();
        }

        @Override
        public ObservableList<Doctor> getFilteredDoctorList() {
            ClinicAssistant clinicAssistant = new ClinicAssistant();
            List<Doctor> doctorList = new ArrayList<>();
            doctorList.add(BENSON);
            clinicAssistant.setDoctors(doctorList);
            return clinicAssistant.getDoctorList();
        }

        @Override
        public void removeAvailableTimeSlot(Timeslot timeslot) {
            return;
        }

        @Override
        public void deleteAppointment(Appointment appointment) {
            appointmentsAdded.remove(appointment);
        }
    }
}
