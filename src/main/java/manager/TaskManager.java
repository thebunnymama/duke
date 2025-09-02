package manager;

import task.Task;

import java.util.*;

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
    public List<Task> getAllTask() {
        return Collections.unmodifiableList(taskList);
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
     * Utility method to validate the given index is within valid range of task list.
     *
     * @param index 0-based index of the task
     * @throws IndexOutOfBoundsException if index is invalid
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
    }
}
