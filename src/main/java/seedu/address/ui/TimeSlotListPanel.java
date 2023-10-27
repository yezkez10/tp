package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.timeslots.Timeslots;

/**
 * Panel containing the list of appointments.
 */
public class TimeSlotListPanel extends UiPart<Region> {
    private static final String FXML = "TimeSlotListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TimeSlotListPanel.class);

    @FXML
    private ListView<Timeslots> timeslotsListView;

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code ObservableList}.
     */
    public TimeSlotListPanel(ObservableList<Timeslots> TimeslotsList) {
        super(FXML);
        timeslotsListView.setItems(TimeslotsList);
        timeslotsListView.setCellFactory(listView -> new TimeSlotListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Appointment} using a {@code AppointmentCard}.
     */
    class TimeSlotListViewCell extends ListCell<Timeslots> {
        @Override
        protected void updateItem(Timeslots timeslot, boolean empty) {
            super.updateItem(timeslot, empty);

            if (empty || timeslot == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TimeSlotCard(timeslot, getIndex() + 1).getRoot());
            }
        }
    }
}