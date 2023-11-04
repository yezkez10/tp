package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EthnicityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ethnicity(null));
    }

    @Test
    public void constructor_invalidEthnicity_throwsIllegalArgumentException() {
        String invalidEthnicity = "CHINESE";
        assertThrows(IllegalArgumentException.class, () -> new Ethnicity(invalidEthnicity));
    }

    @Test
    public void isValidEthnicity() {
        // null ethnicity
        assertThrows(NullPointerException.class, () -> Ethnicity.isValidEthnic(null));

        // invalid ethnicity
        assertFalse(Ethnicity.isValidEthnic("INDIAN")); //upper case input
        assertFalse(Ethnicity.isValidEthnic("")); // empty string
        assertFalse(Ethnicity.isValidEthnic("     ")); // spaces only
        assertFalse(Ethnicity.isValidEthnic("123456789")); // numbers
        assertFalse(Ethnicity.isValidEthnic("&%")); // special characters
        assertFalse(Ethnicity.isValidEthnic("Chi ese")); // spaces within letters

        // Heuristic: test all valid inputs at least once
        assertTrue(Ethnicity.isValidEthnic("Chinese"));
        assertTrue(Ethnicity.isValidEthnic("Malay"));
        assertTrue(Ethnicity.isValidEthnic("Indian"));
        assertTrue(Ethnicity.isValidEthnic("Eurasian"));
        assertTrue(Ethnicity.isValidEthnic("Others"));
    }

    @Test
    public void equals() {
        Ethnicity ethnic = new Ethnicity("Others");

        // same values -> returns true
        assertTrue(ethnic.equals(new Ethnicity("Others")));

        // same object -> returns true
        assertTrue(ethnic.equals(ethnic));

        // null -> returns false
        assertFalse(ethnic.equals(null));

        // different values -> returns false
        assertFalse(ethnic.equals(new Ethnicity("Chinese")));
    }
    @Test
    public void ethnicityToStringReturnsCorrectString() {
        Ethnicity ethnic = new Ethnicity("Indian");
        assertEquals("Indian", ethnic.toString());
    }
}
