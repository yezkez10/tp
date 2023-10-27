package seedu.address.model.timeslots;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A Timeslot class that has a date and hour
 */
public class Timeslots {
    private LocalDate date;
    private int hour;

    public static final String MESSAGE_CONSTRAINTS = "Description must not be empty, Date must be in dd-MM-yyyy HH:mm";

    public static final String MESSAGE_INVALID_DATE = "Date must be in dd-MM-yyyy";

    public static final String MESSAGE_INVALID_DATE_TIME = "Date must be in dd-MM-yyyy HH:mm";
    /*
     * description must be alphanumeric
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructor for Appointment instance
     * @param hour the Timing of the TimeSlot
     * @param date The Date of the TimeSlot
     */
    public Timeslots(LocalDate date, int hour) {
        requireAllNonNull(date, hour);
        this.date = date;
        this.hour = hour;
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
    public static boolean isValidDate(String test) {
        // Define the format that the string should adhere to
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try {
            // Attempt to parse the string into a LocalDateTime using the specified format
            LocalDate parsedDate = LocalDate.parse(test, formatter);
            LocalDate currentDate = LocalDate.now(); // Get the current date and time
            return parsedDate.isAfter(currentDate);
        } catch (DateTimeParseException e) {
            // Parsing failed, so the string is not in the correct format
            return false;
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("date", this.date)
                .add("time", this.hour)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Timeslots)) {
            return false;
        }

        Timeslots timeslot = (Timeslots) other;

        return Objects.equals(hour, timeslot.hour)
                && Objects.equals(date, timeslot.date);
    }

    @Override
    public int hashCode() {
        // Generate a hashCode based on description and dateTime
        return Objects.hash(date, hour);
    }

    /**
     * Checks if 2 Timeslots instances are the same
     * @param otherTimeSlot Other timeslot instance
     * @return A boolean instance whether they are equal
     */
    public boolean isSameTimeSlot(Timeslots otherTimeSlot) {
        if (otherTimeSlot == this) {
            return true;
        }

        return otherTimeSlot != null
                && otherTimeSlot.getDate().equals(getDate())
                && otherTimeSlot.getHour() == getHour();
    }

    public int getHour() {
        return this.hour;
    }
}
