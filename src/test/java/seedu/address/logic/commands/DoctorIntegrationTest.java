package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDoctors.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.doctor.Doctor;
import seedu.address.testutil.DoctorBuilder;

public class DoctorIntegrationTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());;


    @Test
    public void execute_newDoctor_success() {
        Doctor validDoctor = new DoctorBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addDoctor(validDoctor);

        assertCommandSuccess(new AddDoctorCommand(validDoctor), model,
                String.format(AddDoctorCommand.MESSAGE_SUCCESS, Messages.formatDoctor(validDoctor)),
                expectedModel);
    }

    @Test
    public void execute_duplicateDoctor_throwsCommandException() {
        Doctor doctorInList = model.getAddressBook().getDoctorList().get(0);
        assertCommandFailure(new AddDoctorCommand(doctorInList), model,
                AddDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

}
