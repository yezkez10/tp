package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_PATIENTS_FOUND_OVERVIEW = "%1$d patients found!";
    public static final String MESSAGE_ONE_PATIENT_FOUND_OVERVIEW = "1 patient found!";
    public static final String MESSAGE_NO_PATIENT_FOUND_OVERVIEW = "No patients found!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

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
