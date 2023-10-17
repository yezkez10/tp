package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Nric nric;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final ArrayList<Appointment> appointments = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, nric, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nric = nric;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags,
                  ArrayList<Appointment> appointments) {
        requireAllNonNull(name, phone, email, nric, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nric = nric;
        this.address = address;
        this.tags.addAll(tags);
        this.appointments.addAll(appointments);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Nric getNric() {
        return nric;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Adds an appointment to this Person
     * @param toAdd Appointment to be added
     */
    public void addAppointment(Appointment toAdd) {
        this.appointments.add(toAdd);
    }

    /**
     * @param index Index of the appointment to delete
     * @return An ArrayList without the deleted appointment
     */
    public Appointment deleteAppointment(int index) {
        Appointment appt = appointments.get(index);
        this.appointments.remove(index);
        return appt;
    }
    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same name, phone number and nric fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                //&& email.equals(otherPerson.email)
                && nric.equals(otherPerson.nric);
                //&& address.equals(otherPerson.address)
                //&& tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nric, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("nric", nric)
                .add("address", address)
                .add("tags", tags)
                .add("appointments", appointments)
                .toString();
    }

}
