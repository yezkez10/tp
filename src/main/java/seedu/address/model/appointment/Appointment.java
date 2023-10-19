package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Holds the information of the appointment.
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS = "Description must not be empty, Date must be in dd-MM-yyyy HH:mm";

    /*
     * description must be alphanumeric
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String description;
    private final LocalDateTime dateTime;
    private final Person patient;

    /**
     * @param description String of the description of the appointment
     * @param dateTime Information on when this appointment is happening
     */
    public Appointment(String description, LocalDateTime dateTime, Person patient) {
        requireAllNonNull(description, dateTime);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.dateTime = dateTime;
        this.patient = patient;
    }

    /**
     * Returns true if a given string is a valid descrption
     * @param test String to be tested
     * @return True if the string is a valid description
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given LocalDateTime is a valid date and time (must be in the future) for an appointment.
     */
    public static boolean isValidDateTime(String test) {
        // Define the format that the string should adhere to
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        try {
            // Attempt to parse the string into a LocalDateTime using the specified format
            LocalDateTime parsedDateTime = LocalDateTime.parse(test, formatter);
            LocalDateTime currentDateTime = LocalDateTime.now(); // Get the current date and time
            return parsedDateTime.isAfter(currentDateTime);
        } catch (DateTimeParseException e) {
            // Parsing failed, so the string is not in the correct format
            return false;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return "Description: " + description + " \nOn: "
                + dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;

        return Objects.equals(description, otherAppointment.description)
                && Objects.equals(dateTime, otherAppointment.dateTime);
    }

    @Override
    public int hashCode() {
        // Generate a hashCode based on description and dateTime
        return Objects.hash(description, dateTime);
    }

    /**
     * Returns the patient of the appointment.
     *
     * @return Patient of the appointment.
     */
    public Person getPatient() {
        return this.patient;
    }

    /**
     * Returns true if both appointments have the same details.
     */
    public boolean isSameAppointment(Appointment otherAppt) {
        if (otherAppt == this) {
            return true;
        }

        return otherAppt != null
                && otherAppt.getPatient().equals(getPatient())
                && otherAppt.getDateTime().equals(getDateTime());
    }
}
