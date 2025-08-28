package message;

import task.Task;

/**
 * Message displayed when a completed task is marked as pending (unmarked).
 */
public class UnmarkTaskMessage implements Message {
    private final Task task;
    private final int taskIndex;

    public UnmarkTaskMessage(Task task, int index) {
        this.task = task;
        this.taskIndex = index;
    }

    @Override
    public String getMessage() {
        return String.format("OK, I've marked this task as not done yet:\n%s",
                task.toString());
    }
}
