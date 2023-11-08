package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ETHNIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ETHNIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.ClinicAssistant;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withGender("F")
            .withAge(12)
            .withEthnic("Eurasian")
            .withNric("T1234567A")
            .withPhone("94351253")
            .withTags("friends")
            .build();
    public static final Person BENSON = new PersonBuilder()
            .withName("Benson Meier")
            .withPhone("98765432")
            .withEmail("johnd@example.com")
            .withGender("M")
            .withAge(42)
            .withEthnic("Eurasian")
            .withNric("T9876543B")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withTags("owesMoney", "friends")
            .build();
    public static final Person CARL = new PersonBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withGender("M")
            .withAge(22)
            .withEthnic("Others")
            .withNric("S4567890C")
            .withAddress("wall street")
            .build();
    public static final Person DANIEL = new PersonBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withGender("M")
            .withAge(25)
            .withEthnic("Chinese")
            .withNric("S2345678D")
            .withAddress("10th street")
            .withTags("friends")
            .build();
    public static final Person ELLE = new PersonBuilder()
            .withName("Elle Meyer")
            .withPhone("94822244")
            .withEmail("werner@example.com")
            .withGender("F")
            .withAge(32)
            .withEthnic("Eurasian")
            .withNric("T7890123E")
            .withAddress("michegan ave")
            .build();
    public static final Person FIONA = new PersonBuilder()
            .withName("Fiona Kunz")
            .withPhone("94824277")
            .withEmail("lydia@example.com")
            .withGender("F")
            .withAge(120)
            .withEthnic("Malay")
            .withNric("T3456789F")
            .withAddress("little tokyo")
            .build();
    public static final Person GEORGE = new PersonBuilder()
            .withName("George Best")
            .withPhone("94824422")
            .withEmail("anna@example.com")
            .withGender("M")
            .withAge(100)
            .withEthnic("Indian")
            .withNric("S5678901G")
            .withAddress("4th street")
            .build();

    // Manually added
    public static final Person HOON = new PersonBuilder()
            .withName("Hoon Meier")
            .withPhone("84824244")
            .withEmail("stefan@example.com")
            .withGender("F")
            .withAge(0)
            .withEthnic("Chinese")
            .withNric("S6543210H")
            .withAddress("little india")
            .build();
    public static final Person IDA = new PersonBuilder()
            .withName("Ida Mueller")
            .withPhone("84821311")
            .withEmail("hans@example.com")
            .withGender("F")
            .withAge(12)
            .withEthnic("Eurasian")
            .withNric("S4321098I")
            .withAddress("chicago ave")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withGender(VALID_GENDER_AMY)
            .withAge(VALID_AGE_AMY)
            .withEthnic(VALID_ETHNIC_AMY)
            .withNric(VALID_NRIC_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withGender(VALID_GENDER_BOB)
            .withAge(VALID_AGE_BOB)
            .withEthnic(VALID_ETHNIC_BOB)
            .withNric(VALID_NRIC_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static ClinicAssistant getTypicalAddressBook() {
        ClinicAssistant ab = new ClinicAssistant();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
