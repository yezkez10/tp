package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.doctor.UniqueDoctorList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.timeslots.Timeslot;
import seedu.address.model.timeslots.UniqueTimeslotList;
import seedu.address.model.timeslots.exceptions.DuplicateTimeslotException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class ClinicAssistant implements ReadOnlyClinicAssistant {

    private final UniquePersonList persons;
    private final UniqueAppointmentList allAppointments;

    private final UniqueDoctorList doctors;

    private final UniqueTimeslotList allTimeSlots;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        allAppointments = new UniqueAppointmentList();
        doctors = new UniqueDoctorList();
        allTimeSlots = new UniqueTimeslotList();
    }

    public ClinicAssistant() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public ClinicAssistant(ReadOnlyClinicAssistant toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyClinicAssistant newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setAppointments(newData.getAppointmentList());
        setDoctors(newData.getDoctorList());
        setTimeslots(newData.getTimeSlotList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a doctor to clinic assistant.
     * The doctor must not already exist in clinic assistant.
     */
    public void addDoctor(Doctor d) {
        doctors.add(d);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given doctor {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in clinic asisstant.
     * The person identity of {@code editedDoctor} must not be the same as another existing person in the address book.
     */
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireNonNull(editedDoctor);

        doctors.setDoctor(target, editedDoctor);
    }

    /**
     * Returns true if a doctor with the same identity as {@code doctor} exists in clinic assistant.
     */
    public boolean hasDoctor(Doctor doctor) {
        requireNonNull(doctor);
        return doctors.contains(doctor);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeDoctor(Doctor key) {
        doctors.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Doctor> getDoctorList() {
        return doctors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClinicAssistant)) {
            return false;
        }

        ClinicAssistant otherClinicAssistant = (ClinicAssistant) other;
        return persons.equals(otherClinicAssistant.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

    /**
     * Returns a list of appointments.
     *
     * @return List of appointments.
     */
    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return allAppointments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Timeslot> getTimeSlotList() {
        return allTimeSlots.asUnmodifiableObservableList();
    }

    /**
     * Adds a Timeslot instance into the current timeslot list
     * @param timeslot Timeslot instance to be added
     */
    public void addAvailableTimeSlot(Timeslot timeslot) {
        if (!allTimeSlots.contains(timeslot)) {
            allTimeSlots.add(timeslot);
        }
    }

    /**
     * Removes the timeslot instance from the timeslot list
     * @param timeslot Timeslot instance to be removed
     */
    public void removeAvailableTimeSlot(Timeslot timeslot) {
        if (allTimeSlots.size() > 0) {
            //cannot remove if allTimeSlots is not set via View
            allTimeSlots.remove(timeslot);
        }
    }

    /**
     * Clears the list
     */
    public void resetTimeslots() {
        allTimeSlots.clear();
    }


    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return allAppointments.contains(appointment);
    }

    public void addAppointment(Appointment appointment) {
        allAppointments.add(appointment);
    }

    public void addAppointmentAsList(List<Appointment> appointments) {
        this.allAppointments.addAll(appointments);
    }

    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);
        allAppointments.setAppointment(target, editedAppointment);
    }

    /**
     * Sets the current timeslot list to be the one given
     * @param timeSlotsList The timeslot list to be set to
     */
    public void setTimeslots(List<Timeslot> timeSlotsList) {
        allTimeSlots.setTimeslotsList(timeSlotsList);
    }

    public void setAppointments(List<Appointment> appointments) {
        this.allAppointments.setAppointments(appointments);
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors.setDoctors(doctors);
    }

    public void deleteAppointment(Appointment appointment) {
        this.allAppointments.delete(appointment);
    }
}
