package seedu.address.model.timeslots;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.timeslots.exceptions.DuplicateTimeslotException;
import seedu.address.model.timeslots.exceptions.TimeSlotNotFoundException;

/**
 * A UniqueTimeslotList class that is used for JavaFX FrontEnd
 */
public class UniqueTimeslotList implements Iterable<Timeslot> {

    private final ObservableList<Timeslot> internalList = FXCollections.observableArrayList();
    private final ObservableList<Timeslot> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Timeslot as the given argument.
     */
    public boolean contains(Timeslot toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTimeSlot);
    }

    /**
     * Adds a Timeslot to the list if it is not already present.
     * @param toAdd Timeslot instance we are adding to the list
     */
    public void add(Timeslot toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            internalList.add(toAdd);
            FXCollections.sort(internalList, Comparator.comparingInt(Timeslot::getHour));
        }
    }

    /**
     * Clears the list
     */
    public void clear() {
        if (!internalList.isEmpty()) {
            internalList.clear();
        }
    }

    /**
     * Removes the equivalent Timeslot from the list.
     * The Timeslot must exist in the list.
     */
    public void remove(Timeslot toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TimeSlotNotFoundException();
        }
    }

    /**
     * Sets the UniqueTimeslotList from current to the replacement given
     * @param replacement UniqueTimeslotList we want to set current List to
     */
    public void setTimeslots(UniqueTimeslotList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Adds to the contents of this list with {@code timeslot}.
     * {@code timeslot} must not contain duplicate Timeslot.
     */
    public void addAll(List<Timeslot> timeslotsList) {
        requireAllNonNull(timeslotsList);
        if (!timeSlotsAreUnique(timeslotsList)) {
            throw new DuplicateTimeslotException();
        }

        internalList.addAll(timeslotsList);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Timeslot> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void setTimeslotsList(List<Timeslot> timeslotsList) {
        requireAllNonNull(timeslotsList);
        if (!timeSlotsAreUnique(timeslotsList)) {
            throw new DuplicateTimeslotException();
        }
        internalList.setAll(timeslotsList);
    }

    @Override
    public Iterator<Timeslot> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTimeslotList)) {
            return false;
        }

        UniqueTimeslotList otherUniqueTimeslotList = (UniqueTimeslotList) other;
        return internalList.equals(otherUniqueTimeslotList.internalList);
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
     * Returns the size of the UniqueTimeslotList
     * @return Size of the internalList instance
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Returns true if {@code timeslot} contains only unique Timeslot.
     */
    private boolean timeSlotsAreUnique(List<Timeslot> timeslotsList) {
        Set<Timeslot> uniqueTimeslots = new HashSet<>();
        for (Timeslot timeslot : timeslotsList) {
            if (!uniqueTimeslots.add(timeslot)) {
                return false; // Duplicate time slot found
            }
        }

        return true;
    }
}
