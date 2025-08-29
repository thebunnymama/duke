package message;

import manager.TaskManager;
import task.Task;

/**
 * Confirmation message displayed when a task is successfully added.
 * Shows the description of the newly added task.
 */
public class AddTaskMessage implements Message {
    private final Task task;
    private final int totalTasks;

    /**
     * Creates an add task confirmation message.
     *
     * @param task the task that was added
     */
    public AddTaskMessage(Task task, TaskManager tm) {
        this.task = task;
        this.totalTasks = tm.getTotalTasks();
    }

    @Override
    public String getMessage() {
        return String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list.",
                task.toString(),
                totalTasks
        );
    }
}
