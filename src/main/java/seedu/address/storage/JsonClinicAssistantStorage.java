package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyClinicAssistant;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonClinicAssistantStorage implements ClinicAssistantStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonClinicAssistantStorage.class);

    private Path filePath;

    public JsonClinicAssistantStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getClinicAssistantFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClinicAssistant> readClinicAssistant() throws DataLoadingException {
        return readClinicAssistant(filePath);
    }

    /**
     * Similar to {@link #readClinicAssistant()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyClinicAssistant> readClinicAssistant(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableClinicAssistant> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableClinicAssistant.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveClinicAssistant(ReadOnlyClinicAssistant addressBook) throws IOException {
        saveClinicAssistant(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveClinicAssistant(ReadOnlyClinicAssistant)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveClinicAssistant(ReadOnlyClinicAssistant addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClinicAssistant(addressBook), filePath);
    }

}
