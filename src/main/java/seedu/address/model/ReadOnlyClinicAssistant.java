package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.timeslots.Timeslot;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyClinicAssistant {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns a list of appointments.
     *
     * @return List of appointments.
     */
    ObservableList<Appointment> getAppointmentList();

    ObservableList<Timeslot> getTimeSlotList();
    /**
     * Returns true if the appointment list contains an equivalent appointment as the given argument.
     *
     * @param appointment Appointment to check.
     * @return True if the appointment list contains an equivalent appointment as the given argument.
     */
    boolean hasAppointment(Appointment appointment);
}
