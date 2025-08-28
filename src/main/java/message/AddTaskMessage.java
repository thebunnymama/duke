package message;

import task.Task;

/**
 * Confirmation message displayed when a task is successfully added.
 * Shows the description of the newly added task.
 */
public class AddTaskMessage implements Message {
    private final Task task;

    /**
     * Creates an add task confirmation message.
     *
     * @param task the task that was added
     */
    public AddTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessage() {
        return String.format("Added: %s", task.getDescription());
    }
}
