package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
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
import seedu.address.model.util.SampleDataUtil;

public class DoctorBuilder {

    public static final String DEFAULT_NAME = "Kevin";
    public static final String DEFAULT_PHONE = "85355256";
    public static final String DEFAULT_EMAIL = "kevin@gmail.com";
    public static final String DEFAULT_GENDER = "F";
    public static final int DEFAULT_AGE = 21;
    public static final String DEFAULT_ADDRESS = "123, Jurong East Ave 6, #08-123";

    private Name name;
    private Phone phone;
    private Email email;
    private Gender gender;
    private Age age;
    private Address address;
    private ArrayList<Appointment> appointments;

    /**
     * Creates a {@code DoctorBuilder} with the default details.
     */
    public DoctorBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        gender = new Gender(DEFAULT_GENDER);
        age = new Age(DEFAULT_AGE);
        address = new Address(DEFAULT_ADDRESS);
    }

    /**
     * Initializes the DoctorBuilder with the data of {@code doctorToCopy}.
     */
    public DoctorBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        gender = personToCopy.getGender();
        age = personToCopy.getAge();
        address = personToCopy.getAddress();
        appointments = new ArrayList<>(personToCopy.getAppointments());
    }

    /**
     * Sets the {@code Name} of the {@code Doctor} that we are building.
     */
    public DoctorBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public DoctorBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public DoctorBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public DoctorBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public DoctorBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }
    /**
     * Sets the {@code Age} of the {@code Person} that we are building.
     */
    public DoctorBuilder withAge(int age) {
        this.age = new Age(age);
        return this;
    }

    public Doctor build() {
        return new Doctor(name, phone, email, gender, age, address);
    }

}
