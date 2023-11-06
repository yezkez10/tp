package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTimeslots.DEFAULT_TIMESLOT;
import static seedu.address.testutil.TypicalTimeslots.TIMESLOT_TWO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;

/**
 * Contains unit and integration tests for {@code ViewAvailableCommand}.
 */
public class ViewAvailableCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        LocalDate defaultDate = DEFAULT_TIMESLOT.getDate();
        OnDateTimeApptPredicate appointmentPredicate =
                new OnDateTimeApptPredicate(defaultDate);
        OnDateTimeSlotPredicate timeslotPredicate =
                new OnDateTimeSlotPredicate(defaultDate);

        LocalDate dateTwo = TIMESLOT_TWO.getDate();
        OnDateTimeApptPredicate appointmentPredicateTwo =
                new OnDateTimeApptPredicate(dateTwo);
        OnDateTimeSlotPredicate timeslotPredicateTwo =
                new OnDateTimeSlotPredicate(dateTwo);

        ViewAvailableCommand firstViewCommand = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, defaultDate);
        ViewAvailableCommand secondViewCommand = new ViewAvailableCommand(timeslotPredicateTwo,
                appointmentPredicateTwo, dateTwo);

        // same object -> returns true
        assertTrue(firstViewCommand.equals(firstViewCommand));

        // same values -> returns true
        ViewAvailableCommand firstViewCommandCopy = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, timeslotPredicate.getDate());
        assertTrue(firstViewCommand.equals(firstViewCommandCopy));

        // different types -> returns false
        assertFalse(firstViewCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewCommand.equals(null));

        // different person -> returns false
        assertFalse(firstViewCommand.equals(secondViewCommand));
    }

    @Test//Integration
    public void populateUnavailableTimeslotTest_success() {
        LocalDate date = prepareDate("05-02-2024");
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(date);
        OnDateTimeApptPredicate apptPredicate = new OnDateTimeApptPredicate(date);
        ViewAvailableCommand viewAvailableCommand = new ViewAvailableCommand(predicate, apptPredicate, date);
        ObservableList<Appointment> appointmentList = model.getFilteredAppointmentList();
        Set<Integer> setResult = viewAvailableCommand.populateUnavailableTimeslot(appointmentList);
        Set<Integer> setExpected = new HashSet<>();
        for (Appointment appt: appointmentList) {
            if (appt.getDateTime().toLocalDate().equals(date)) {
                setExpected.add(appt.getDateTime().getHour());
            }
        }
        assertEquals(setExpected, setResult);
    }

    @Test//Integration
    public void addAvailableTimeslotsEmptySet_success() {//Heuristic: Equivalence Partition: EmptySet/valid set
        LocalDate date = prepareDate("05-02-2024");
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(date);
        OnDateTimeApptPredicate appointmentPredicate = new OnDateTimeApptPredicate(date);
        ViewAvailableCommand viewAvailableCommand = new ViewAvailableCommand(predicate, appointmentPredicate, date);
        //total = 9 timeslots for the day
        int sizeExpected = 9;
        viewAvailableCommand.addAvailableTimeslotsToModel(new HashSet<>(), model);
        assertEquals(sizeExpected, model.getAvailableTimeSlotList().size());
    }

    @Test//Integration
    public void addAvailableTimeslotsToModel_success() {
        LocalDate date = prepareDate("05-02-2024");
        OnDateTimeSlotPredicate predicate = new OnDateTimeSlotPredicate(date);
        OnDateTimeApptPredicate appointmentPredicate = new OnDateTimeApptPredicate(date);
        ViewAvailableCommand viewAvailableCommand = new ViewAvailableCommand(predicate, appointmentPredicate, date);
        ObservableList<Appointment> appointmentList = model.getFilteredAppointmentList();
        Set<Integer> unavailableTimeslot = viewAvailableCommand.populateUnavailableTimeslot(appointmentList);
        //total = 9 timeslots for the day
        int sizeExpected = 9 - unavailableTimeslot.size();
        viewAvailableCommand.addAvailableTimeslotsToModel(unavailableTimeslot, model);
        assertEquals(sizeExpected, model.getAvailableTimeSlotList().size());
    }

    @Test
    public void toStringMethod() {
        LocalDate defaultDate = DEFAULT_TIMESLOT.getDate();
        OnDateTimeApptPredicate appointmentPredicate =
                new OnDateTimeApptPredicate(defaultDate);
        OnDateTimeSlotPredicate timeslotPredicate =
                new OnDateTimeSlotPredicate(defaultDate);
        ViewAvailableCommand viewAvailableCommand = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, defaultDate);
        String expected = ViewAvailableCommand.class.getCanonicalName() + "{predicate=" + timeslotPredicate + "}";
        assertEquals(expected, viewAvailableCommand.toString());
    }

    private LocalDate prepareDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
