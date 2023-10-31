package seedu.address.model.timeslots.exceptions;

/**
 * Signals that the operation will result in duplicate Timeslot.
 */
public class DuplicateTimeslotException extends RuntimeException {
    public DuplicateTimeslotException() {
        super("Operation would result in duplicate timeslot!");
    }
}
