package seedu.address.model.timeslots.exceptions;

/**
 * Signals that the operation is unable to find the specified timeslot.
 */
public class TimeSlotNotFoundException extends RuntimeException {
    public TimeSlotNotFoundException() {
        super();
    }
}
