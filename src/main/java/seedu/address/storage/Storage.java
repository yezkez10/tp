package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyClinicAssistant;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ClinicAssistantStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getClinicAssistantFilePath();

    @Override
    Optional<ReadOnlyClinicAssistant> readClinicAssistant() throws DataLoadingException;

    @Override
    void saveClinicAssistant(ReadOnlyClinicAssistant addressBook) throws IOException;

}
