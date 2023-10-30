package seedu.address.model.doctor.exceptions;

/**
 * Signals that the operation will result in duplicate Doctors (Doctors are considered duplicates if they have the same
 * identity).
 */
public class DuplicateDoctorException extends RuntimeException {
    public DuplicateDoctorException() {
        super("This will result in duplicate Doctor");
    }
}
