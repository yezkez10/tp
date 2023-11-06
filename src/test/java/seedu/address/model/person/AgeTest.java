package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {
    @Test
    public void constructor_negativeAge_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Age(-1));
    }
    @Test
    public void constructor_impossibleAge_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Age(151));
    }

    @Test
    public void isValidAge() {
        // invalid age
        assertFalse(Age.isValidAge(-1)); //lower case input
        assertFalse(Age.isValidAge(151)); //too high

        assertTrue(Age.isValidAge(0));
        assertTrue(Age.isValidAge(1));
    }

    @Test
    public void equals() {
        Age age = new Age(12);

        // same values -> returns true
        assertTrue(age.equals(new Age(12)));

        // same object -> returns true
        assertTrue(age.equals(age));

        // different values -> returns false
        assertFalse(age.equals(new Age(60)));
    }
    @Test
    public void ageToStringShouldReturnCorrectStringValue() {
        Age age = new Age(10);
        assertEquals("10", age.toString());
    }
}
