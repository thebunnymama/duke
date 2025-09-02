package message;

import manager.TaskManager;
import task.Task;

/**
 * Confirmation message displayed when a task is successfully deleted.
 * Shows the description of the deleted task.
 */
public class TaskDeletedMessage implements Message {
    private final Task task;
    private final int taskCount;

    /**
     * Creates a delete task confirmation message.
     *
     * @param task the task that was added
     */
    public TaskDeletedMessage(Task task, TaskManager tm) {
        this.task = task;
        this.taskCount = tm.getTotalTasks();
    }

    @Override
    public String getMessage() {
        return String.format("Bye bye '%s'! Removed like unwanted beansprouts from your laksa.\nNow you have %d tasks in the list.",
                task.toString(),
                taskCount
        );
    }
}
