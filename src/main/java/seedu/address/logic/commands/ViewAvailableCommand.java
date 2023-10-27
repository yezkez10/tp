package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.timeslots.Timeslots;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class ViewAvailableCommand extends Command {//view /on <date>
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " /on DATE";

    private Predicate<Timeslots> predicate;
    private Predicate<Appointment> apptPredicate;
    private LocalDate dateEntered;

    public ViewAvailableCommand(Predicate<Timeslots> predicate, Predicate<Appointment> apptPredicate, LocalDate dateEntered) {
        this.predicate = predicate;
        this.apptPredicate = apptPredicate;
        this.dateEntered = dateEntered;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(apptPredicate);
        ObservableList<Appointment> appointmentList = model.getFilteredAppointmentList(); //list of apptments for that DATE
        Set<Integer> unavailableTimeslots = new HashSet<>();

        // populate unavailableTimeslots
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment currAppt = appointmentList.get(i);
            unavailableTimeslots.add(currAppt.getDateTime().getHour());
        }

        //creates a list of timeslot from 9am to 5pm
        for (int i = 9; i < 18; i++) {
            if (unavailableTimeslots.contains(i)) {
                continue;
            }
            Timeslots timeslots = new Timeslots(dateEntered, i);
            model.addAvailableTimeSlot(timeslots);
        }
        //assert
        //format for = 0
        System.out.println(model.getAvailableTimeSlotList().toString());

        // Return success command in command prompt
        return new CommandResult(Messages.MESSAGE_AVAILABLE_TIMESLOTS_FOUND_OVERVIEW);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindAppointmentsCommand)) {
            return false;
        }
        ViewAvailableCommand otherViewCommand = (ViewAvailableCommand) other;
        return predicate.equals(otherViewCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
