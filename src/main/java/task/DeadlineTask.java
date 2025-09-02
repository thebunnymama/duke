package task;

/**
 * Task with a specific date/time. Time format is preserved as entered by user.
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
     * Extends base format with deadline information in the format "(by: deadline)".
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format(" (by: %s)", deadline);
    }
}
