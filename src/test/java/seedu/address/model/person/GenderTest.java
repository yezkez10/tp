package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "Male";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null gender
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid genders : Heuristic = Equivalence Partitioning: empty/whitespace/invalid/numbers/special characters
        assertFalse(Gender.isValidGender("m")); //lower case input
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender("     ")); // spaces only
        assertFalse(Gender.isValidGender("MALEFEMALE")); //alphabets only
        assertFalse(Gender.isValidGender("123456789")); // numbers
        assertFalse(Gender.isValidGender("&%")); // contains non-numeric & non-alpha characters
        assertFalse(Gender.isValidGender("M F")); // spaces within digits

        // Heuristic: test all valid inputs at least once
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("F"));
    }

    @Test
    public void equals() {
        Gender gender = new Gender("M");

        // same values -> returns true
        assertTrue(gender.equals(new Gender("M")));

        // same object -> returns true
        assertTrue(gender.equals(gender));

        // null -> returns false
        assertFalse(gender.equals(null));

        // different values -> returns false
        assertFalse(gender.equals(new Gender("F")));
    }
}
