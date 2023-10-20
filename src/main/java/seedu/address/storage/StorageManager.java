package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyClinicAssistant;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ClinicAssistantStorage clinicAssistantStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ClinicAssistantStorage clinicAssistantStorage, UserPrefsStorage userPrefsStorage) {
        this.clinicAssistantStorage = clinicAssistantStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getClinicAssistantFilePath() {
        return clinicAssistantStorage.getClinicAssistantFilePath();
    }

    @Override
    public Optional<ReadOnlyClinicAssistant> readClinicAssistant() throws DataLoadingException {
        return readClinicAssistant(clinicAssistantStorage.getClinicAssistantFilePath());
    }

    @Override
    public Optional<ReadOnlyClinicAssistant> readClinicAssistant(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return clinicAssistantStorage.readClinicAssistant(filePath);
    }

    @Override
    public void saveClinicAssistant(ReadOnlyClinicAssistant addressBook) throws IOException {
        saveClinicAssistant(addressBook, clinicAssistantStorage.getClinicAssistantFilePath());
    }

    @Override
    public void saveClinicAssistant(ReadOnlyClinicAssistant addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        clinicAssistantStorage.saveClinicAssistant(addressBook, filePath);
    }

}
