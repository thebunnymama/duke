package message;

import task.Task;

/**
 * Message displayed when a task is successfully marked as done.
 */
public class MarkTaskDoneMessage implements Message {
    private final Task task;
    private final int taskIndex;

    public MarkTaskDoneMessage(Task task, int index) {
        this.task = task;
        this.taskIndex = index;
    }

    @Override
    public String getMessage() {
        return String.format("Nice! I've marked this task as done:\n%s",
                task.toString());
    }
}
