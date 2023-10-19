package seedu.address.testutil;

import seedu.address.model.ClinicAssistant;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ClinicAssistant clinicAssistant;

    public AddressBookBuilder() {
        clinicAssistant = new ClinicAssistant();
    }

    public AddressBookBuilder(ClinicAssistant clinicAssistant) {
        this.clinicAssistant = clinicAssistant;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        clinicAssistant.addPerson(person);
        return this;
    }

    public ClinicAssistant build() {
        return clinicAssistant;
    }
}
