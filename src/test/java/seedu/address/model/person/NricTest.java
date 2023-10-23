package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "A12345B";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nrics
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("ABCDEFGHI")); // alpha characters only
        assertFalse(Nric.isValidNric("123456789")); // digits only, no alpha characters
        assertFalse(Nric.isValidNric("98765432A")); // no alpha character at the beginning
        assertFalse(Nric.isValidNric("XY123456Z")); // less than 7 numbers between 2 alpha characters
        assertFalse(Nric.isValidNric("T012:45&E")); // contains non-numeric & non-alpha characters
        assertFalse(Nric.isValidNric("T901p041E")); // alphabets within digits
        assertFalse(Nric.isValidNric("T312 153E")); // spaces within digits

        // valid nrics
        assertTrue(Nric.isValidNric("T1234567A"));
        assertTrue(Nric.isValidNric("t1234567a")); //lowercase alpha characters
    }

    @Test
    public void equals() {
        Nric nric = new Nric("T1234567A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("T1234567A")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // same sequence in lowercase -> returns true
        assertTrue(nric.equals("t1234567a"));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T1234321A")));
    }
}
