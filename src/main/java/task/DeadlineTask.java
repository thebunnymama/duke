package task;

/**
 * Task with a specific date/time
 */
public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string representation of this DeadlineTask.
     * It includes the common task information from the parent class,
     * followed by the deadline information in the format "(by: deadline)".
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format(" (by: %s)", deadline);
    }
}
