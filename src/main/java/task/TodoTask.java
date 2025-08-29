package task;

/**
 * Simple todo task without any date/time constraints
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
