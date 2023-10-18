package seedu.address.model.appointment;

import seedu.address.commons.util.ToStringBuilder;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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

    /**
     * @param description String of the description of the appointment
     * @param dateTime Information on when this appointment is happening
     */
    public Appointment(String description, LocalDateTime dateTime) {
        requireAllNonNull(description, dateTime);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid descrption
     * @param test
     * @return
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
        return new ToStringBuilder(this)
                .add("description", description)
                .add("dateTime", dateTime)
                .toString();
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
}
