package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ClinicAssistant;
import seedu.address.model.ReadOnlyClinicAssistant;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Email;
import seedu.address.model.person.Ethnicity;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Gender("M"), new Age(21), new Ethnicity("Chinese"),
                        new Nric("T1341367E"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("type 1 diabetes")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Gender("F"), new Age(16), new Ethnicity("Chinese"),
                        new Nric("T1231437E"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("throat infection", "on antibiotics")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Gender("F"), new Age(60), new Ethnicity("Eurasian"),
                        new Nric("T5443267E"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("smoker")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Gender("M"), new Age(55), new Ethnicity("Chinese"),
                        new Nric("T1290127E"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("vapes")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Gender("M"), new Age(42), new Ethnicity("Malay"),
                        new Nric("T1432567E"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("blood type: A")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Gender("M"), new Age(33), new Ethnicity("Indian"),
                        new Nric("T1236312E"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("just had hand surgery"))
        };
    }

    public static ReadOnlyClinicAssistant getSampleAddressBook() {
        ClinicAssistant sampleAb = new ClinicAssistant();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
