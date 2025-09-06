package message;

import task.Task;

/**
 * Message displayed when a task is successfully marked as done.
 */
public class TaskMarkedMessage implements Message {
    private final Task task;
    private final int taskIndex;

    public TaskMarkedMessage(Task task, int taskIndex) {
        this.task = task;
        this.taskIndex = taskIndex;
    }

    @Override
    public String message() {
        return String.format("""
                '%s' accomplished like a true blue Singaporean - efficient and effective!
                That's one less thing to mee-ddle with!
                """, task.toString());
    }
}
