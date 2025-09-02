package message;

import manager.TaskManager;
import task.Task;

/**
 * Confirmation message displayed when a task is successfully added.
 * Shows the description of the newly added task.
 */
public class TaskAddedMessage implements Message {
    private final Task task;
    private final int taskCount;

    /**
     * Creates an add task confirmation message.
     *
     * @param task the task that was added
     */
    public TaskAddedMessage(Task task, TaskManager tm) {
        this.task = task;
        this.taskCount = tm.getTotalTasks();
    }

    @Override
    public String getMessage() {
        return String.format("Got it. I've added this task:\n %s\n Now you have %d tasks in the list.",
                task.toString(),
                taskCount
        );
    }
}
