package manager;

import exception.InvalidTaskOperationException;
import task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Owns the data model and manages task collection with basic CRUD operations.
 * Tasks are stored in insertion order and accessed by 0-based index internally.
 */
public class TaskManager {

    private final List<Task> taskList = new ArrayList<>();
    private boolean isSorted = false;

    /**
     * Adds task to end of collection.
     */
    public void addTask(Task task) {
        taskList.add(task);
        isSorted = false;
    }

    /**
     * Returns read-only view to prevent external modification of internal list
     * while allowing safe iteration and access for UI components.
     * <p>Any attempt to modify the returned list will result in an {@code UnsupportedOperationException}.
     *
     * @return an unmodifiable view of the current task list
     * @see Collections#unmodifiableList(List)
     */
    public List<Task> getReadOnlyList() {
        return Collections.unmodifiableList(taskList);
    }

    /**
     * Returns a modifiable list for authorized operations (e.g., saving to file).
     * <p>
     * This method provides controlled access to the internal task list by requiring
     * a valid Storage.Key instance. Only Storage instance can create valid tokens,
     * ensuring direct modification of the task list is restricted to authorized operations.
     *
     * @param token a valid Storage.Key instance that authorizes privileged access
     * @return the internal modifiable list of tasks
     * @throws NullPointerException if key is null
     */
    public List<Task> getModifiableList(Storage.Key token) {
        Objects.requireNonNull(token);
        return taskList;
    }

    /**
     * Retrieves a task by its position (1-based index) in the list.
     * The method internally converts to 0-based indexing for list access.
     *
     * @param userIndex 1-based index of the task
     * @throws InvalidTaskOperationException if the index is out of bounds (<1 or >the list size)
     * @see #validateIndex(int)
     */
    public Task getTask(int userIndex) throws InvalidTaskOperationException {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        return taskList.get(actualIndex);
    }

    /**
     * Marks a task as completed by index.
     * <p>If the task is already marked as done, an exception is thrown to prevent redundant operations.
     *
     * @param userIndex 1-based index as provided by user
     * @throws InvalidTaskOperationException if the index is out of bounds or
     *         the task is already marked as completed
     * @see #validateIndex(int)
     * @see #validateTaskState(int, boolean)
     */
    public void markTaskDone(int userIndex) throws InvalidTaskOperationException {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        validateTaskState(actualIndex, true);
        Task task = taskList.get(actualIndex);
        task.markAsDone();
        isSorted = false;
    }

    /**
     * Unmarks a completed task by index.
     * <p>If the task is already marked as pending, an exception is thrown to prevent redundant operations.
     *
     * @param userIndex 1-based index as provided by user
     * @throws InvalidTaskOperationException if the index is out of bounds or
     *         the task is already marked as completed
     * @see #validateIndex(int)
     * @see #validateTaskState(int, boolean)
     */
    public void unmarkTask(int userIndex) throws InvalidTaskOperationException {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        validateTaskState(actualIndex, false);
        Task task = taskList.get(actualIndex);
        task.markAsUndone();
        isSorted = false;
    }

    /**
     * Removes a task from the list by index.
     *
     * @param userIndex 1-based index as provided by user
     * @throws InvalidTaskOperationException if the index is out of bounds
     * @see #validateIndex(int)
     */
    public void deleteTask(int userIndex) throws InvalidTaskOperationException {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        Task task = taskList.get(actualIndex);
        taskList.remove(task);
    }

    /**
     * Sorts the task list in chronological order based on their first date.
     * <p>This is a stable sort and uses natural ordering, i.e. tasks without date are placed last.
     * Comparator implementation referenced from:
     * <a href="https://dev.java/learn/lambdas/writing-comparators/">...</a>
     */
    public void sortByDate() {
        taskList.sort(Comparator.comparing(
                task -> task.getDates().isEmpty() ? null : task.getDates().get(0),
                Comparator.nullsLast(Comparator.naturalOrder())
        ));
        isSorted = true;
    }

    /**
     * Sorts the task list by completion status. This is a stable sort.
     * <p>Tasks are sorted with incomplete tasks appearing first, followed by completed tasks.
     */
    public void sortByStatus() {
        taskList.sort(Comparator.comparing(Task::isDone));
        isSorted = true;
    }

    /**
     * Checks if the task list was sorted.
     */
    public boolean isSorted() {
        return isSorted;
    }

    /**
     * Checks if the task list is empty.
     *
     * @return {@code true} if no task exist
     */
    public boolean isEmpty() {
        return getTotalTasks() < 1;
    }

    /**
     * Returns total number of tasks (complete and pending).
     */
    public int getTotalTasks() {
        return taskList.size();
    }

    /**
     * Filters tasks from the task list based on the provided predicate condition.
     * <p> A new list containing only tasks that satisfy the given condition is created.
     * The original task list remains unchanged. Tasks are evaluated in their current order,
     * and matching tasks maintain their relative ordering in the result.
     *
     * @param condition a Predicate that defines the filtering criteria;
     *                  tasks for which this returns {@code true} are included
     * @return a new list containing only tasks that match the condition, or
     *         an empty list if no tasks match
     */
    public List<Task> filter(Predicate<Task> condition) {
        List<Task> filteredResults = new ArrayList<>();
        for (Task task : taskList) {
            if (condition.test(task)) {
                filteredResults.add(task);
            }
        }
        return filteredResults;
    }

    /**
     * Validates that a task is not already in the desired completion state to prevent
     * redundant mark/unmark operations.
     *
     * @param index 0-based index of the task to validate
     * @param markDone the desired completion state ({@code true} for done,
     *        {@code false} for pending)
     * @throws InvalidTaskOperationException if task already in desired state
     */
    private void validateTaskState(int index, boolean markDone) throws InvalidTaskOperationException {
        Task task = taskList.get(index);
        if (markDone == task.isDone()) {
            String state = markDone ? "done" : "unmarked";
            String undo = markDone ? "unmark" : "mark";
            throw new InvalidTaskOperationException(
                    InvalidTaskOperationException.ErrorType.TASK_STATE,
                    state, undo, index + 1
            );
        }
    }

    /**
     *Validates that the given index is within the valid range of task list.
     *
     * @param index 0-based index of the task to validate
     * @throws InvalidTaskOperationException if index is negative or
     *         greater than or equal to the list size
     */
    private void validateIndex(int index) throws InvalidTaskOperationException {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskOperationException(
                    InvalidTaskOperationException.ErrorType.TASK_NOT_FOUND,
                    index + 1
            );
        }
    }
}
