package seedu.address.model.person;

import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEthnic(String)}
 */
public class Ethnicity {
    public static final String MESSAGE_CONSTRAINTS =
            "Ethnic should be 'Chinese'/ 'Malay'/ 'Indian'/ 'Eurasian' or 'Others' ";
    private static final Set<String> VALID_ETHNICITIES =
            Set.of("Chinese", "Malay", "Indian", "Eurasian", "Others");
    public final String ethnic;

    /**
     * Constructs a {@code Gender}.
     *
     * @param ethnic A valid Ethnicity.
     */
    public Ethnicity(String ethnic) {
        requireNonNull(ethnic);
        checkArgument(isValidEthnic(ethnic), MESSAGE_CONSTRAINTS);
        this.ethnic = ethnic;
    }

    /**
     * Returns true if a given string is a valid Ethnicity.
     */
    public static boolean isValidEthnic(String test) {
        return VALID_ETHNICITIES.contains(test);
    }

    @Override
    public String toString() {
        return this.ethnic;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Ethnicity)) {
            return false;
        }

        Ethnicity otherEthnicity = (Ethnicity) other;
        return this.ethnic.equals(otherEthnicity.ethnic);
    }

    @Override
    public int hashCode() {
        return ethnic.hashCode();
    }

}
