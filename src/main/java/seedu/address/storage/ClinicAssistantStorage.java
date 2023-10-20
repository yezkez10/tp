package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ClinicAssistant;
import seedu.address.model.ReadOnlyClinicAssistant;

/**
 * Represents a storage for {@link ClinicAssistant}.
 */
public interface ClinicAssistantStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClinicAssistantFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyClinicAssistant}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyClinicAssistant> readClinicAssistant() throws DataLoadingException;

    /**
     * @see #getClinicAssistantFilePath()
     */
    Optional<ReadOnlyClinicAssistant> readClinicAssistant(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyClinicAssistant} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClinicAssistant(ReadOnlyClinicAssistant addressBook) throws IOException;

    /**
     * @see #saveClinicAssistant(ReadOnlyClinicAssistant)
     */
    void saveClinicAssistant(ReadOnlyClinicAssistant addressBook, Path filePath) throws IOException;

}
