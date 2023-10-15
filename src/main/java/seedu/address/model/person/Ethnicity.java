package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEthnic(String)}
 */
public enum Ethnicity {
    CHINESE("Chinese"), MALAY("Malay"), INDIAN("Indian"), EURASIAN("Eurasian"), OTHERS("Others");

    public static final String MESSAGE_CONSTRAINTS =
            "Ethnic should be Chinese/ Malay/ Indian/ Eurasian or Others";
    public final String ethnic;

    /**
     * Constructs a {@code Gender}.
     *
     * @param ethnic A valid Ethnicity.
     */
    Ethnicity(String ethnic) {
        requireNonNull(ethnic);
        this.ethnic = ethnic;
    }

    /**
     * Returns true if a given string is a valid Ethnicity.
     */
    public static boolean isValidEthnic(String test) {
        for (Ethnicity e: Ethnicity.values()){
            if (e.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.ethnic;
    }

//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof Ethnicity)) {
//            return false;
//        }
//
//        Ethnicity otherEthnicity = (Ethnicity) other;
//        return this.ethnic.equals(otherEthnicity.ethnic);
//    }

}
