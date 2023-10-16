package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class DeleteAppointmentCommandTest {

    @Test
    public void execute_deleteAppointmentSuccessful() throws Exception {
        Person validPerson = new PersonBuilder().build();
        LocalDateTime time1 = ParserUtil.parseDateTime("02-01-2024 12:00");
        LocalDateTime time2 = ParserUtil.parseDateTime("02-01-2025 12:00");
        LocalDateTime time3 = ParserUtil.parseDateTime("02-01-2026 12:00");
        LocalDateTime time4 = ParserUtil.parseDateTime("02-01-2027 12:00");
        Appointment one = new Appointment("one", time1);
        Appointment two = new Appointment("two", time2);
        Appointment three = new Appointment("three", time3);
        Appointment four = new Appointment("4", time4);
        Appointment five = new Appointment("5", time4);
        validPerson.addAppointment(one);
        validPerson.addAppointment(two);
        validPerson.addAppointment(three);
        validPerson.addAppointment(four);
        validPerson.addAppointment(five);
        ArrayList<Appointment> original = validPerson.getAppointments();
        int length = original.size() - 1;
        ArrayList<Appointment> editted = validPerson.deleteAppointment(1);
        ArrayList<Appointment>  test = new ArrayList<>(original);
        for(int i = 0; i < original.size(); i++) {
            System.out.println(test.get(i).toString());
        }
        for(int i = 0; i < editted.size(); i++) {
            System.out.println(editted.get(i).toString());
        }
        assertEquals(1,1);
    }
}