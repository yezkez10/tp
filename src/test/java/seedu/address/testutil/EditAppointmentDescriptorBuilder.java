package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {

    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code EditAppointmentDescriptor}'s details
     * @param descriptor
     */
    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setDescription(appointment.getDescription());
        descriptor.setDateTime(appointment.getDateTime());
        descriptor.setPatient(appointment.getPatient());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDescription(String desc) {
        descriptor.setDescription(desc);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        descriptor.setDateTime(LocalDateTime.parse(dateTime, formatter));
        return this;
    }
    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
