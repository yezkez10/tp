package seedu.address.model.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.doctor.exceptions.DoctorNotFoundException;
import seedu.address.model.doctor.exceptions.DuplicateDoctorException;

/**
 * A list of doctors that enforces uniqueness between its elements and does not allow nulls.
 * A doctor is considered unique by comparing using {@code Doctor#isSameDoctor(Doctor)}. As such, adding and updating of
 * persons uses Doctor#isSameDoctor(Doctor) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Doctor#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Doctor#isSameDoctor(Doctor)
 */
public class UniqueDoctorList implements Iterable<Doctor> {
    private final ObservableList<Doctor> internalList = FXCollections.observableArrayList();
    private final ObservableList<Doctor> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent  Doctor as the given argument.
     */
    public boolean contains(Doctor toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDoctor);
    }

    /**
     * Adds a doctor to the list.
     * The doctor must not already exist in the list.
     */
    public void add(Doctor toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDoctorException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the doctor {@code target} in the list with {@code editedDoctor}.
     * {@code target} must exist in the list.
     * The doctor identity of {@code editedDoctor} must not be the same as another existing doctor in the list.
     */
    public void setDoctor(Doctor target, Doctor editedDoctor) {
        requireAllNonNull(target, editedDoctor);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DoctorNotFoundException();
        }

        if (!target.isSameDoctor(editedDoctor) && contains(editedDoctor)) {
            throw new DuplicateDoctorException();
        }

        internalList.set(index, editedDoctor);
    }

    /**
     * Removes the equivalent doctor from the list.
     * The doctor must exist in the list.
     */
    public void remove(Doctor toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DoctorNotFoundException();
        }
    }

    public void setDoctors(UniqueDoctorList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code doctors}.
     * {@code doctors} must not contain duplicate doctors.
     */
    public void setDoctors(List<Doctor> doctors) {
        requireAllNonNull(doctors);
        if (!doctorsAreUnique(doctors)) {
            throw new DuplicateDoctorException();
        }

        internalList.setAll(doctors);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Doctor> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Doctor> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueDoctorList)) {
            return false;
        }

        UniqueDoctorList otherUniqueDoctorList = (UniqueDoctorList) other;
        return internalList.equals(otherUniqueDoctorList.internalList);
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
     * Returns true if {@code doctors} contains only unique doctors.
     */
    private boolean doctorsAreUnique(List<Doctor> doctors) {
        for (int i = 0; i < doctors.size() - 1; i++) {
            for (int j = i + 1; j < doctors.size(); j++) {
                if (doctors.get(i).isSameDoctor(doctors.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
