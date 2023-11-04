package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.timeslots.Timeslot;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPTS = unused -> true;
    Predicate<Timeslot> PREDICATE_SHOW_ALL_TIMESLOTS = unused -> true;

    Predicate<Doctor> PREDICATE_SHOW_ALL_DOCTORS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyClinicAssistant addressBook);

    /** Returns the AddressBook */
    ReadOnlyClinicAssistant getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Adds timeslot instance into the list
     * @param timeslot Timeslot instance to be added
     */
    void addAvailableTimeSlot(Timeslot timeslot);

    /**
     * Removes timeslot instance from the list
     * @param timeslot Timeslot instance to be removed
     */
    void removeAvailableTimeSlot(Timeslot timeslot);

    public void resetAvailableTimeSlot();

    /**
     * Updates the available timeslot list according to a given predicate
     * @param predicate Predicate instance wrapped around a Timeslot
     */
    void updateFilteredAvailableTimeslot(Predicate<Timeslot> predicate);

    /**
     * Gets the List of available timeslot
     * @return ObservableList of Timeslot for JavaFX
     */
    ObservableList<Timeslot> getAvailableTimeSlotList();

    /**
     * Adds an appointment to the address book.
     *
     * @param toAdd Appointment to be added.
     */
    void addAppointment(Appointment toAdd);

    /**
     * Deletes an appointment in the address book.
     */
    void deleteAppointment(Appointment appointment);

    /**
     * Replaces Appointment with updated Appointment
     *
     * @param target Appointment to replace
     * @param editedAppointment edited Appointment to replace with
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    void editedPersonAppointments(ArrayList<Appointment> oldAppointments, ArrayList<Appointment> toReplace);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     *
     * @param editedAppointment Appointment to check.
     * @return true if appointment exists.
     */
    boolean hasAppointment(Appointment editedAppointment);

    /**
     * Updates the filter of the filtered doctor list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDoctorList(Predicate<Doctor> predicate);

    /**
     * Adds a doctor to clinic assistant.
     *
     * @param toAdd Doctor to be added.
     */
    void addDoctor(Doctor toAdd);

    /**
     * Deletes a doctor in clinic assistant.
     */
    void deleteDoctor(Doctor appointment);

    /**
     * Replaces Doctor with updated Doctor.
     *
     * @param target doctor to replace
     * @param editedDoctor edited doctor to replace with
     */
    void setDoctor(Doctor target, Doctor editedDoctor);

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     *
     * @param editedDoctor Appointment to check.
     * @return true if appointment exists.
     */
    boolean hasDoctor(Doctor editedDoctor);

    /** Returns an unmodifiable view of the filtered doctor list */
    ObservableList<Doctor> getFilteredDoctorList();

    ObservableList<Timeslot> getFilteredTimeslotsList();

    Predicate<Timeslot> getCurrentPredicate();
}
