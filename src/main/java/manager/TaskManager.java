package manager;

import message.Message;
import task.Task;

import java.util.*;

/**
 * Manages a collection of tasks with basic CRUD operations.
 * Tasks are stored in insertion order and accessed by index.
 */
public class TaskManager {

    private final List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns an unmodifiable view of list of tasks.
     * The returned list cannot be modified, protecting the internal data structure
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
     * Marks a task as completed by index (1-based for user display).
     *
     * @param userIndex 1-based index as provided by user
     * @return Modified task object for caller to handle
     */
    public Task markTaskDone(int userIndex) {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        Task task = taskList.get(actualIndex);
        task.markAsDone();
        return task;
    }

    /**
     * Unmarks a task by index (1-based for user display).
     *
     * @param userIndex 1-based index as provided by user
     * @return Modified task object for caller to handle
     */
    public Task unmarkTask(int userIndex) {
        int actualIndex = userIndex - 1;    // convert to 0-based index
        validateIndex(actualIndex);
        Task task = taskList.get(actualIndex);
        task.markAsUndone();
        return task;
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
