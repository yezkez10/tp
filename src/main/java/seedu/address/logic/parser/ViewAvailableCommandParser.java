package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewAvailableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;
import seedu.address.model.timeslots.Timeslots;

import java.time.LocalDate;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

public class ViewAvailableCommandParser implements Parser<ViewAvailableCommand> {
    public ViewAvailableCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        //verify no duplicate input date
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE);

        //verify required argument is present
        if (argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAvailableCommand.MESSAGE_USAGE));
        }

        Predicate<Timeslots> predicate = null;
        Predicate<Appointment> apptPredicate = null;
        LocalDate date = null;
        // Check if required arguments are present
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String dateStr = argMultimap.getValue(PREFIX_DATE).get().trim();

            if (dateStr.isEmpty() || dateStr.equals("\n")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewAvailableCommand.MESSAGE_USAGE));
            }

            date = ParserUtil.parseDate(dateStr);

            predicate = new OnDateTimeSlotPredicate(date);
            apptPredicate = new OnDateTimeApptPredicate(date);
        }

        return new ViewAvailableCommand(predicate, apptPredicate, date);
    }
}
