package task;

/**
 * Simple task without date/time constraints.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
       super(description);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }
}
