package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TimeslotBuilder.DEFAULT_DATE;
import static seedu.address.testutil.TypicalAppointments.ALICE_WITH_APPOINTMENT;
import static seedu.address.testutil.TypicalTimeslots.DEFAULT_TIMESLOT;
import static seedu.address.testutil.TypicalTimeslots.TIMESLOT_TWO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.NameContainsKeywordsApptPredicate;
import seedu.address.model.appointment.OnDateTimeApptPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.timeslots.OnDateTimeSlotPredicate;
import seedu.address.testutil.TypicalTimeslots;

import javax.swing.text.DateFormatter;
import javax.swing.text.View;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewAvailableCommand}.
 */
public class ViewAvailableCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        LocalDate DEFAULT_DATE = DEFAULT_TIMESLOT.getDate();
        OnDateTimeApptPredicate appointmentPredicate =
                new OnDateTimeApptPredicate(DEFAULT_DATE);
        OnDateTimeSlotPredicate timeslotPredicate =
                new OnDateTimeSlotPredicate(DEFAULT_DATE);

        LocalDate DATE_TWO = TIMESLOT_TWO.getDate();
        OnDateTimeApptPredicate appointmentPredicateTwo =
                new OnDateTimeApptPredicate(DATE_TWO);
        OnDateTimeSlotPredicate timeslotPredicateTwo =
                new OnDateTimeSlotPredicate(DATE_TWO);

        ViewAvailableCommand firstViewCommand = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, DEFAULT_DATE);
        ViewAvailableCommand secondViewCommand = new ViewAvailableCommand(timeslotPredicateTwo,
                appointmentPredicateTwo, DATE_TWO);

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

//    @Test
//    public void execute_zeroKeywords_noAvailableTimeslotFound() {
//        String expectedMessage = MESSAGE_NO_AVAILABLE_TIMESLOTS_OVERVIEW;
//        NameContainsKeywordsApptPredicate appointmentPredicate = preparePredicate(" ");
//        OnDateTimeSlotPredicate timeslotPredicate =
//                new OnDateTimeSlotPredicate(DEFAULT_TIMESLOT.getDate());
//        ViewAvailableCommand ViewCommand = new ViewAvailableCommand(timeslotPredicate,
//                appointmentPredicate, timeslotPredicate.getDate());
//        expectedModel.updateFilteredAppointmentList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
//    }

    @Test
    public void toStringMethod() {
        LocalDate DEFAULT_DATE = DEFAULT_TIMESLOT.getDate();
        OnDateTimeApptPredicate appointmentPredicate =
                new OnDateTimeApptPredicate(DEFAULT_DATE);
        OnDateTimeSlotPredicate timeslotPredicate =
                new OnDateTimeSlotPredicate(DEFAULT_DATE);
        ViewAvailableCommand viewAvailableCommand = new ViewAvailableCommand(timeslotPredicate,
                appointmentPredicate, DEFAULT_DATE);
        String expected = ViewAvailableCommand.class.getCanonicalName() + "{predicate=" + timeslotPredicate + "}";
        assertEquals(expected, viewAvailableCommand.toString());
    }

//    @Test
//    public void execute_nameAndDate_appointmentsFound() {
//        String expectedMessage = String.format(MESSAGE_AVAILABLE_TIMESLOTS_FOUND_OVERVIEW,
//                DEFAULT_DATE, DEFAULT_DATE, DEFAULT_DATE);
//        OnDateTimeSlotPredicate datePredicate = preparePredicate(DEFAULT_DATE);
//
//        Predicate<Appointment> apptPredicate = namePredicate.and(datePredicate);
//        FindAppointmentsCommand command = new FindAppointmentsCommand(nameAndDatePredicate);
//        expectedModel.updateFilteredAppointmentList(nameAndDatePredicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(ALICE_WITH_APPOINTMENT.firstAppointment()),
//                model.getFilteredAppointmentList());
//    }

//    @Test
//    public void execute_date_TimeslotsFound() {
//        LocalDate date = prepareDate(DEFAULT_DATE);
//        String expectedMessage = String.format(MESSAGE_AVAILABLE_TIMESLOTS_FOUND_OVERVIEW,
//                date, date, date);
//        OnDateTimeSlotPredicate datePredicate = preparePredicate(DEFAULT_DATE);
//        OnDateTimeApptPredicate apptPredicate = new OnDateTimeApptPredicate(prepareDate(DEFAULT_DATE));
//        ViewAvailableCommand command = new ViewAvailableCommand(datePredicate,
//                apptPredicate, prepareDate(DEFAULT_DATE));
//        expectedModel.updateFilteredAvailableTimeslot(datePredicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//
////        assertEquals(Arrays.asList(ALICE_WITH_APPOINTMENT.firstAppointment()),
////                model.getFilteredTimeslotsList());
//    }

    /**
     * Parses {@code userInput} into a {@code OnDateTimeSlotPredicate}.
     */
    private OnDateTimeSlotPredicate preparePredicate(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(userInput, formatter);
        return new OnDateTimeSlotPredicate(date);
    }

    private LocalDate prepareDate (String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }
}
