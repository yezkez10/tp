package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOR;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * A AppointmentAddCommand instance to add appointment to patients
 */
public class AppointmentAddCommand extends Command {
    public static final String COMMAND_WORD = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a appointment to the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "DESCRIPTION, DATE_TIME (must be a valid date in the future)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FOR + " 1 "
            + PREFIX_DESCRIPTION + " description details "
            + PREFIX_DATE + " 02-01-2024 12:00";

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists for the patient.";

    public static final String MESSAGE_ADD_APPOINTMENT_SUCCESS = "New appointment added: %1$s";

    private final Index targetIndex;
    private final String description;
    private final LocalDateTime dateTime;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AppointmentAddCommand(Index targetIndex, String description, LocalDateTime dateTime) {
        this.targetIndex = targetIndex;
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person targetPatient = lastShownList.get(targetIndex.getZeroBased());

        Appointment toAdd = new Appointment(description, dateTime);
        if (targetPatient.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        targetPatient.addAppointment(toAdd);

        return new CommandResult(String.format(MESSAGE_ADD_APPOINTMENT_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentAddCommand)) {
            return false;
        }

        AppointmentAddCommand otherAddCommand = (AppointmentAddCommand) other;
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
