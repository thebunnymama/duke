package message;

import manager.TaskManager;
import task.Task;

/**
 * Confirmation message when a task is successfully added.
 * Shows the description of the newly added task and current task count.
 */
public class TaskAddedMessage implements Message {
    private final Task task;
    private final int taskCount;

    public TaskAddedMessage(Task task, TaskManager tm) {
        this.task = task;
        this.taskCount = tm.getTotalTasks();
    }

    @Override
    public String getMessage() {
        return String.format("Added!\n'%s' is now one of the %d tasks simmering.",
                task.toString(),
                taskCount
        );
    }
}
