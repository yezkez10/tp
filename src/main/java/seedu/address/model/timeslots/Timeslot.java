package seedu.address.model.timeslots;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * A Timeslot class that has a date and hour
 */
public class Timeslot {
    private LocalDate date;
    private int hour;

    /**
     * Constructor for Timeslot instance
     * @param hour the Timing of the Timeslot
     * @param date The Date of the Timeslot
     */
    public Timeslot(LocalDate date, int hour) {
        requireAllNonNull(date, hour);
        this.date = date;
        this.hour = hour;
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

        if (!(other instanceof Timeslot)) {
            return false;
        }

        Timeslot timeslot = (Timeslot) other;

        return Objects.equals(hour, timeslot.hour)
                && Objects.equals(date, timeslot.date);
    }

    @Override
    public int hashCode() {
        // Generate a hashCode based on description and dateTime
        return Objects.hash(date, hour);
    }

    /**
     * Checks if 2 Timeslot instances are the same
     * @param otherTimeSlot Other Timeslot instance
     * @return A boolean instance whether they are equal
     */
    public boolean isSameTimeSlot(Timeslot otherTimeSlot) {
        if (otherTimeSlot == this) {
            return true;
        }

        return otherTimeSlot != null
                && otherTimeSlot.getDate().equals(getDate())
                && otherTimeSlot.getHour() == getHour();
    }

    /**
     * Gets the Hour of this Timeslot instance
     * @return int instance of hour of Timeslot
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Gets the LocalDate of this Timeslot instance
     * @return LocalDate instance of the date of Timeslot
     */
    public LocalDate getDate() {
        return this.date;
    }
}
