package seedu.address.model.timeslots;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Timeslots}'s {@code Date} matches the Date given.
 */
public class OnDateTimeSlotPredicate implements Predicate<Timeslots> {
    private final LocalDate date;

    /**
     * Constructor for OnDateTimeSlotPredicate
     * @param date The date instance wrapped by the Predicate
     */
    public OnDateTimeSlotPredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Timeslots timeslot) {
        return timeslot.getDate().equals(this.date);
    }

    /**
     * Gets the LocalDate date stored in this Predicate instance
     * @return The date instance wrapped in the Predicate
     */
    public LocalDate getDate() {
        return this.date;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OnDateTimeSlotPredicate)) {
            return false;
        }

        OnDateTimeSlotPredicate otherPredicate = (OnDateTimeSlotPredicate) other;
        return date.equals(otherPredicate.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("date", date)
                .toString();
    }
}
