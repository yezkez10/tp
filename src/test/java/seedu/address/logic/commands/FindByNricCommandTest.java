package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_NO_PATIENT_FOUND_OVERVIEW;
import static seedu.address.logic.Messages.MESSAGE_ONE_PATIENT_FOUND_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NricContainsKeywordPredicate;

public class FindByNricCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NricContainsKeywordPredicate firstPredicate =
                new NricContainsKeywordPredicate("first");
        NricContainsKeywordPredicate secondPredicate =
                new NricContainsKeywordPredicate("second");

        FindByNricCommand findFirstCommand = new FindByNricCommand(firstPredicate);
        FindByNricCommand findSecondCommand = new FindByNricCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByNricCommand findFirstCommandCopy = new FindByNricCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = MESSAGE_NO_PATIENT_FOUND_OVERVIEW;
        NricContainsKeywordPredicate predicate = preparePredicate(" ");
        FindByNricCommand command = new FindByNricCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneKeyword_onePersonFound() {
        String expectedMessage = MESSAGE_ONE_PATIENT_FOUND_OVERVIEW;
        NricContainsKeywordPredicate predicate = preparePredicate("S4567890C");
        FindByNricCommand command = new FindByNricCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NricContainsKeywordPredicate predicate = new NricContainsKeywordPredicate("keyword");
        FindByNricCommand findCommand = new FindByNricCommand(predicate);
        String expected = FindByNricCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }


    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NricContainsKeywordPredicate preparePredicate(String userInput) {
        return new NricContainsKeywordPredicate(userInput);
    }

}
