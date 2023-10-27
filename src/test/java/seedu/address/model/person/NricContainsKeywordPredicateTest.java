package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NricContainsKeywordPredicateTest {
    //todo
    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        NricContainsKeywordPredicate firstPredicate = new NricContainsKeywordPredicate(firstPredicateKeyword);
        NricContainsKeywordPredicate secondPredicate = new NricContainsKeywordPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NricContainsKeywordPredicate firstPredicateCopy = new NricContainsKeywordPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nricContainsKeyword_returnsTrue() {
        // One keyword
        NricContainsKeywordPredicate predicate = new NricContainsKeywordPredicate("S1234567N");
        assertTrue(predicate.test(new PersonBuilder().withNric("S1234567N").build()));

        // case-insensitive keyword
        predicate = new NricContainsKeywordPredicate("s1234567n");
        assertTrue(predicate.test(new PersonBuilder().withNric("S1234567N").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // empty keyword
        NricContainsKeywordPredicate predicate = new NricContainsKeywordPredicate("");
        assertFalse(predicate.test(new PersonBuilder().withNric("S1234567N").build()));

        // Non-matching keyword
        predicate = new NricContainsKeywordPredicate("S1235467N");
        assertFalse(predicate.test(new PersonBuilder().withNric("S1234567N").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "S1234567N";
        NricContainsKeywordPredicate predicate = new NricContainsKeywordPredicate(keyword);

        String expected = NricContainsKeywordPredicate.class.getCanonicalName() + "{keyword=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
