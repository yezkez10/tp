package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.doctor.Doctor;

/**
 * Adds a Doctor to clinic assistant.
 */
public class AddDoctorCommand extends Command {
    public static final String COMMAND_WORD = "add_doctor";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Doctor to clinic assistant. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_AGE + "AGE "
            + PREFIX_ADDRESS + "ADDRESS \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GENDER + "M "
            + PREFIX_AGE + "22 "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_SUCCESS = "New Doctor added: %1$s";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "This Doctor already exists in the system";
    private final Doctor toAdd;

    /**
     * Creates an AddDoctorCommand to add the specified {@code Doctor}
     */
    public AddDoctorCommand(Doctor doctor) {
        requireNonNull(doctor);
        toAdd = doctor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasDoctor(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCTOR);
        }

        model.addDoctor(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatDoctor(toAdd)));
    }

}
