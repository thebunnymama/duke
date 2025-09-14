package manager;

import exception.InvalidTaskIndexException;
import storage.Storage;
import task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Owns the data model and manages task collection with basic CRUD operations.
 * Tasks are stored in insertion order and accessed by 0-based index internally.
 */
public class TaskManager {

    private final List<Task> taskList = new ArrayList<>();

    /**
     * Adds task to end of collection.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns read-only view to prevent external modification of internal list
     * while allowing safe iteration and access for UI components.
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
     *
     * @param userIndex 1-based index of the task
     */
    public Task getTask(int userIndex) {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        return taskList.get(actualIndex);
    }

    /**
     * Marks a task as completed by index.
     *
     * @param userIndex 1-based index as provided by user
     */
    public void markTaskDone(int userIndex) {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        Task task = taskList.get(actualIndex);
        task.markAsDone();
    }

    /**
     * Unmarks a completed task by index.
     *
     * @param userIndex 1-based index as provided by user
     */
    public void unmarkTask(int userIndex) {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        Task task = taskList.get(actualIndex);
        task.markAsUndone();
    }

    /**
     * Removes a task by index.
     *
     * @param userIndex 1-based index as provided by user
     */
    public void deleteTask(int userIndex) {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        Task task = taskList.get(actualIndex);
        taskList.remove(task);
    }

    /**
     * Returns total number of tasks (complete and pending).
     */
    public int getTotalTasks() {
        return taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if no task exist
     */
    public boolean isEmpty() {
        return getTotalTasks() < 1;
    }

    /**
     * Generic filter method
     *
     * @param condition a Predicate that defines which tasks to keep
     * @return filtered list of tasks
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
     * Utility method to validate the given index is within valid range of task list.
     *
     * @param index 0-based index of the task
     * @throws InvalidTaskIndexException if index is invalid
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
    }
}
