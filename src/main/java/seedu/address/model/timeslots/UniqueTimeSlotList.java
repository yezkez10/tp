package seedu.address.model.timeslots;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.timeslots.exceptions.DuplicateTimeslotException;
import seedu.address.model.timeslots.exceptions.TimeSlotNotFoundException;

/**
 * A UniqueTimeSlotList class that is used for JavaFX FrontEnd
 */
public class UniqueTimeSlotList implements Iterable<Timeslots> {

    private final ObservableList<Timeslots> internalList = FXCollections.observableArrayList();
    private final ObservableList<Timeslots> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Timeslot as the given argument.
     */
    public boolean contains(Timeslots toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTimeSlot);
    }

    /**
     * Adds a Timeslot to the list if it is not already present.
     * @param toAdd Timeslots instance we are adding to the list
     */
    public void add(Timeslots toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            internalList.add(toAdd);
            FXCollections.sort(internalList, Comparator.comparingInt(Timeslots::getHour));
        }
    }


    /**
     * Removes the equivalent Timeslot from the list.
     * The Timeslot must exist in the list.
     */
    public void remove(Timeslots toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TimeSlotNotFoundException();
        }
    }

    /**
     * Sets the UniqueTimeSlotList from current to the replacement given
     * @param replacement UniqueTimeSlotList we want to set current List to
     */
    public void setTimeslots(UniqueTimeSlotList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code timeslots}.
     * {@code timeslots} must not contain duplicate Timeslots.
     */
    public void setTimeslots(List<Timeslots> timeSlotsList) {
        requireAllNonNull(timeSlotsList);
        if (!timeSlotsAreUnique(timeSlotsList)) {
            throw new DuplicateTimeslotException();
        }

        internalList.setAll(timeSlotsList);
    }

    /**
     * Adds to the contents of this list with {@code timeslots}.
     * {@code timeslots} must not contain duplicate Timeslots.
     */
    public void addAll(List<Timeslots> timeslotsList) {
        requireAllNonNull(timeslotsList);
        if (!timeSlotsAreUnique(timeslotsList)) {
            throw new DuplicateTimeslotException();
        }

        internalList.addAll(timeslotsList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Timeslots> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void setTimeslotsList(List<Timeslots> timeslotsList) {
        requireNonNull(timeslotsList);
        if (!timeSlotsAreUnique(timeslotsList)) {
            throw new DuplicateTimeslotException();
        }
    }

    @Override
    public Iterator<Timeslots> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTimeSlotList)) {
            return false;
        }

        UniqueTimeSlotList otherUniqueTimeSlotList = (UniqueTimeSlotList) other;
        return internalList.equals(otherUniqueTimeSlotList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns the size of the UniqueTimeSlotList
     * @return Size of the internalList instance
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns true if {@code timeslots} contains only unique Timeslots.
     */
    private boolean timeSlotsAreUnique(List<Timeslots> timeslotsList) {
        for (int i = 0; i < timeslotsList.size() - 1; i++) {
            for (int j = i + 1; j < timeslotsList.size(); j++) {
                if (timeslotsList.get(i).isSameTimeSlot(timeslotsList.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
