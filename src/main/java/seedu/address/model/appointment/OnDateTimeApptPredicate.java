package seedu.address.model.appointment;

import java.time.LocalDate;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that an {@code Apppointment}'s {@code DateTime} matches the DateTime given.
 */
public class OnDateTimeApptPredicate implements Predicate<Appointment> {
    private final LocalDate date;

    public OnDateTimeApptPredicate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getDateTime().toLocalDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OnDateTimeApptPredicate)) {
            return false;
        }

        OnDateTimeApptPredicate otherPredicate = (OnDateTimeApptPredicate) other;
        return date.equals(otherPredicate.date);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("date", date)
                .toString();
    }
}
