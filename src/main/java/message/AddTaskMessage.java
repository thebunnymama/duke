package message;

import task.Task;

public class AddTaskMessage implements Message {
    private final Task task;

    public AddTaskMessage(Task task) {
        this.task = task;
    }

    @Override
    public String getMessage() {
        return "added: " + task.getDescription();
    }
}
