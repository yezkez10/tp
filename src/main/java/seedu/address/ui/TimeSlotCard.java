package seedu.address.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.timeslots.Timeslot;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class TimeSlotCard extends UiPart<Region> {

    private static final String FXML = "TimeSlotCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Timeslot timeslot;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label hour;

    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} and index to display.
     */
    public TimeSlotCard(Timeslot timeslot) {
        super(FXML);
        this.timeslot = timeslot;
        int hour24 = timeslot.getHour();
        SimpleDateFormat dateFormat = new SimpleDateFormat("h a");
        String hour12 = dateFormat.format(new Date(0, 0, 0, hour24, 0));
        hour.setText(hour12);
    }
}
