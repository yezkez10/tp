package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_AGE_SHOULD_BE_INTEGER;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Ethnicity;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "INDEX entered must be an integer!.";
    public static final String MESSAGE_INDEX_TOO_SMALL = "INDEX should be a positive integer!";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed. This is specifically used for DeleteCommand.
     * @throws ParseException if the specified index is invalid (less than 1 or not an integer).
     */
    public static Index parseDeleteIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.isEmpty()) {
            throw new ParseException("");
        }
        int index;
        try {
            index = Integer.parseInt(trimmedIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        if (index < 1) {
            throw new ParseException(MESSAGE_INDEX_TOO_SMALL);
        }
        return Index.fromOneBased(index);
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (trimmedName.equals("") || !Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (trimmedPhone.equals("") || !Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String nric} into a {@code NRIC}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        String allCapsNric = trimmedNric.toUpperCase();
        if (allCapsNric.equals("") || !Nric.isValidNric(allCapsNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(allCapsNric);
    }

    /**
     * Parses a {@code String Gender} into a {@code Gender}.
     * @param gender The input string that represents Gender "M" or "F"
     * @return Gender instance
     * @throws ParseException if the given {@code gender} is not "M" or "F".
     */
    public static Gender parseGender(String gender) throws ParseException {
        requireNonNull(gender);
        String trimmedGender = gender.trim();
        if (trimmedGender.equals("") || !Gender.isValidGender(trimmedGender)) {
            throw new ParseException(Gender.MESSAGE_CONSTRAINTS);
        }
        return new Gender(trimmedGender);
    }

    /**
     * Parses a {@code String age} into a {@code Age}.
     * @param age The input string that represents age of patient
     * @return Age an Age instance
     * @throws ParseException if the given {@code age} is not between 0 and 150.
     */
    public static Age parseAge(String age) throws ParseException {
        requireNonNull(age);
        String trimmedAge = age.trim();
        int ageInt;
        // Check if trimmedAge is an empty string
        if (trimmedAge.equals("")) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        try {
            ageInt = Integer.parseInt(trimmedAge);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_AGE_SHOULD_BE_INTEGER);
        }

        if (!Age.isValidAge(ageInt)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(ageInt);
    }
    /**
     * Parses a {@code String ethnic} into a {@code Ethnicity}.
     * @param ethnic The input string that represents ethnic group of patient
     * @return Gender instance
     * @throws ParseException if the given {@code ethnic} is not "Chinese", "Malay", "Indian", "Eurasian" or "Others".
     */
    public static Ethnicity parseEthnic(String ethnic) throws ParseException {
        requireNonNull(ethnic);
        String trimmedEthnic = ethnic.trim();
        if (trimmedEthnic.equals("") || !Ethnicity.isValidEthnic(trimmedEthnic)) {
            throw new ParseException(Ethnicity.MESSAGE_CONSTRAINTS);
        }
        return new Ethnicity(trimmedEthnic);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (trimmedAddress.equals("") || !Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (trimmedEmail.equals("") || !Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }


    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (!Appointment.isValidDateTime(trimmedDateTime)) {
            throw new ParseException(Appointment.MESSAGE_INVALID_DATE_TIME);
        }
        LocalDateTime parsedDateTime = LocalDateTime.parse(trimmedDateTime, formatter);
        return parsedDateTime;
    }

    /**
     * Parses a {@code String date} into an {@code LocalDate}.
     *
     * @param date String to be parsed.
     * @return LocalDate instance.
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();

        if (!isValidDate(trimmedDate)) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(trimmedDate, formatter);

        return parsedDate;
    }

    /**
     * Checks if a given string is a valid date.
     *
     * @param date String to be checked.
     * @return True if the string is a valid date, false otherwise.
     */
    public static boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if dateStr is a valid date on calender
     * @param dateStr String instance of date to check
     * @return True as long as Date is on the Calender
     */
    public static boolean isValidDateOnCalendar(String dateStr) {
        try {
            String trimmedDateStr = dateStr.substring(0, 10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(trimmedDateStr, formatter);
            int day = Integer.valueOf(trimmedDateStr.substring(0, 2));
            int month = date.getMonthValue();
            return day >= 1 && day <= date.lengthOfMonth() && month >= 1 && month <= 12;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if a given string is a past date.
     *
     * @param date LocalDate to be checked.
     * @return True if the date is a past date, false otherwise.
     */
    public static boolean isPastDate(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        return date.isBefore(currentDate);
    }
}
