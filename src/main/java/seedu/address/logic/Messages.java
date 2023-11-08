package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX = "The doctor index provided is invalid";
    public static final String MESSAGE_PATIENTS_FOUND_OVERVIEW = "%1$d patients found!";
    public static final String MESSAGE_ONE_PATIENT_FOUND_OVERVIEW = "1 patient found!";
    public static final String MESSAGE_NO_PATIENT_FOUND_OVERVIEW = "No patients found!";
    public static final String MESSAGE_APPOINTMENTS_FOUND_OVERVIEW = "%1$d appointments found!";
    public static final String MESSAGE_ONE_APPOINTMENT_FOUND_OVERVIEW = "1 appointment found!";
    public static final String MESSAGE_NO_APPOINTMENTS_FOUND_OVERVIEW = "No appointments found!";
    public static final String MESSAGE_AVAILABLE_TIMESLOTS_FOUND_OVERVIEW =
            "All Available Timeslot(s) on %tb %td, %tY Listed!";
    public static final String MESSAGE_NO_AVAILABLE_TIMESLOTS_OVERVIEW =
            "No Available Timeslots Available on %tb %td, %tY!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
            "The Appointment index provided is invalid";
    public static final String MESSAGE_INVALID_DATE = "Date must be in dd-MM-yyyy";
    public static final String MESSAGE_DATE_DOES_NOT_EXIST = "Date must be a valid date that exists on the calendar!";


    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    //TODO: change this to own format
    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append(" | Phone: ")
                .append(person.getPhone())
                .append(" | Email: ")
                .append(person.getEmail())
                .append(" | Gender: ")
                .append(person.getGender())
                .append(" | Age: ")
                .append(person.getAge())
                .append(" | Ethnic: ")
                .append(person.getEthnic())
                .append(" | NRIC: ")
                .append(person.getNric())
                .append(" | Address: ")
                .append(person.getAddress())
                .append(" | Tags: ");
        person.getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the {@code doctor} for display to the user.
     */
    public static String formatDoctor(Doctor doctor) {
        final StringBuilder builder = new StringBuilder();
        builder.append(doctor.getName())
                .append(" | Phone: ")
                .append(doctor.getPhone())
                .append(" | Email: ")
                .append(doctor.getEmail())
                .append(" | Gender: ")
                .append(doctor.getGender())
                .append(" | Age: ")
                .append(doctor.getAge())
                .append(" | Address: ")
                .append(doctor.getAddress());
        return builder.toString();
    }
    /**
     * Formats the {@code address of the patient} for display to the user.
     * @param person Patient we are interested in
     * @param appointment Appointment of the patient
     * @return
     */
    public static String formatAppointment(Person person, Appointment appointment) {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nPatient: ")
                .append(person.getName())
                .append("\n")
                .append("Description: ")
                .append(appointment.getDescription())
                .append("\n")
                .append("Date: ")
                .append(appointment.getDateTime());
        return builder.toString();
    }
}
