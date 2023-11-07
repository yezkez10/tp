package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAge(int)}
 */
public class Age {

    public static final String MESSAGE_CONSTRAINTS =
            "Age should be <150 and >= 0";
    public final int age;

    /**
     * Constructs a {@code Gender}.
     *
     * @param age A valid Age.
     */
    public Age(int age) {
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        this.age = age;
    }

    /**
     * Returns true if a given input is a valid age.
     */
    public static boolean isValidAge(int test) {
        return test <= 150 && test >= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Age)) {
            return false;
        }

        Age otherAge = (Age) other;
        return this.age == otherAge.age;
    }

    @Override
    public String toString() {
        return String.valueOf(this.age);
    }
}
