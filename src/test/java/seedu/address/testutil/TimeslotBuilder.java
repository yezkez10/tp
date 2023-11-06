package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.timeslots.Timeslot;

/**
 * A utility class to help with building Timeslot objects.
 */
public class TimeslotBuilder {
    public static final String DEFAULT_DATE = "02-01-2024";
    public static final int DEFAULT_HOUR = 15;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private LocalDate date;
    private int hour;

    /**
     * Creates a {@code TimeslotBuilder} with the default details
     */
    public TimeslotBuilder() {
        date = LocalDate.parse(DEFAULT_DATE, formatter);
        this.hour = DEFAULT_HOUR;
    }

    /**
     * Creates a {@code TimeslotBuilder} with the date and hour of the other timeslot
     */
    public TimeslotBuilder(Timeslot other) {
        date = other.getDate();
        hour = other.getHour();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Timeslot} that we are building.
     */
    public TimeslotBuilder withDate(String date) {
        this.date = LocalDate.parse(date, formatter);
        return this;
    }

    /**
     * Sets the date of this instance
     * @param date of this instance we want to set to
     * @return this TimeslotBuilder instance
     */
    public TimeslotBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Timeslot} that we are building.
     */
    public TimeslotBuilder withHour(int hour) {
        this.hour = hour;
        return this;
    }

    /**
     * Returns a Timeslot instance with the values set in TimeslotBuilder
     * @return Timeslot with the correct date and hour
     */
    public Timeslot build() {
        return new Timeslot(this.date, this.hour);
    }
}
