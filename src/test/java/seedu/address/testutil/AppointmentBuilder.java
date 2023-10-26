package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Person objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_DESCRIPTION = "Description of Appointment";
    public static final String DEFAULT_DATE = "01-01-2024 00:00";
    public static final Person DEFAULT_PERSON = ALICE;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private String description;
    private LocalDateTime dateTime;
    private Person person;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public AppointmentBuilder(Person person) {
        description = DEFAULT_DESCRIPTION;
        dateTime = LocalDateTime.parse(DEFAULT_DATE, formatter);
        this.person = person;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public AppointmentBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public AppointmentBuilder withDateTme(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
        return this;
    }

    /**
     * Returns person with Appointment added to him
     *
     * @return Person with Appointment
     */
    public Person build() {
        Appointment appointment = new Appointment(description, dateTime, person);
        person.addAppointment(appointment);
        return person;
    }

}
