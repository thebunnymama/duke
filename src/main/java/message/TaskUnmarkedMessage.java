package message;

import task.Task;

/**
 * Message displayed when a completed task is marked as pending (unmarked).
 */
public class TaskUnmarkedMessage implements Message {
    private final Task task;
    private final int taskIndex;

    public TaskUnmarkedMessage(Task task, int index) {
        this.task = task;
        this.taskIndex = index;
    }

    @Override
    public String message() {
        return String.format("'%s' is back to pending - just like when hawker uncle changes his mind about closing time",
                task.toString());
    }
}
