package message;

import manager.TaskManager;
import task.Task;

/**
 * Confirmation message when a task is successfully deleted.
 * Shows the description of the deleted task and current task count.
 */
public class TaskDeletedMessage implements Message {
    private final Task task;
    private final int taskCount;

    public TaskDeletedMessage(Task task, TaskManager tm) {
        this.task = task;
        this.taskCount = tm.getTotalTasks();
    }

    @Override
    public String message() {
        return String.format("""
                        Bye bye '%s'!
                        Removed like unwanted beansprouts from your laksa.
                        Now you have %d tasks in the list.""",
                task.toString(),
                taskCount
        );
    }
}
