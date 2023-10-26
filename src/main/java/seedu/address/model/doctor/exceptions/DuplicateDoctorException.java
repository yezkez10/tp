package seedu.address.model.doctor.exceptions;

public class DuplicateDoctorException extends RuntimeException {
    public DuplicateDoctorException() {
        super("This will result in duplicate Doctor");
    }
}
