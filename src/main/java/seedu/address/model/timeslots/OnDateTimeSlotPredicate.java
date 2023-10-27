package seedu.address.model.timeslots;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Apppointment}'s {@code DateTime} matches the DateTime given.
 */
public class OnDateTimeSlotPredicate implements Predicate<Timeslots> {
    private final LocalDate date;

    public OnDateTimeSlotPredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Timeslots timeslot) {
        return timeslot.getDate().equals(this.date);
    }

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
