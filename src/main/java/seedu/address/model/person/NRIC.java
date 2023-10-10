package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NRIC in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNRIC(String)}
 */
public class NRIC {

    public static final String MESSAGE_CONSTRAINTS =
            "NRICs should contain 7 digits, with 2 capital letters at the beginning and end";
    public static final String VALIDATION_REGEX = "\\p{Alpha}\\d{7}\\p{Alpha}";
    public final String value;

    /**
     * Constructs a {@code NRIC}.
     *
     * @param nric A valid NRIC.
     */
    public NRIC(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNRIC(nric), MESSAGE_CONSTRAINTS);
        value = nric;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidNRIC(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NRIC)) {
            return false;
        }

        NRIC otherNRIC = (NRIC) other;
        return value.equals(otherNRIC.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}