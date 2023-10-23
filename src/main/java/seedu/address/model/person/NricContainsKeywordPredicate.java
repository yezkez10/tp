package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NricContainsKeywordPredicate implements Predicate<Person> {
    private final String keyword;

    public NricContainsKeywordPredicate(String keyword) {
        this.keyword = keyword.toUpperCase();
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals(person.getNric().value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NricContainsKeywordPredicate)) {
            return false;
        }

        NricContainsKeywordPredicate otherNricContainsKeywordPredicate = (NricContainsKeywordPredicate) other;
        return keyword.equals(otherNricContainsKeywordPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
