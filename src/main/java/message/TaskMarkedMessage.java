package message;

import task.Task;

/**
 * Message displayed when a task is successfully marked as done.
 */
public class TaskMarkedMessage implements Message {
    private final Task task;

    public TaskMarkedMessage(Task task) {
        this.task = task;
    }

    @Override
    public String message() {
        return String.format("""
                '%s' accomplished like a true blue Singaporean - efficient and effective!
                That's one less thing to mee-ddle with!
                """, task.toString());
    }
}
