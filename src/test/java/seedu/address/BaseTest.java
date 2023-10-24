package seedu.address;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonClinicAssistantStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

import java.nio.file.Path;

import static seedu.address.testutil.TestUtil.getTypicalAddressBook;

public class BaseTest {

    protected Model model;
    protected Model expectedModel;
    protected ClinicAssistant ca;
    @TempDir
    public Path testFolder;
    public StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ca = getTypicalAddressBook();

        JsonClinicAssistantStorage addressBookStorage = new JsonClinicAssistantStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
        // Other common setup code
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @AfterEach
    public void tearDown() {
        model = null;
        expectedModel = null;
        ca = null;
        storageManager = null;
        // Other common teardown code
    }
}