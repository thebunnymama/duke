package task;

/**
 * Task with a start and end date/time
 */
public class EventTask extends Task {
    private String eventTime;

    public EventTask(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Returns a string representation of this DeadlineTask.
     * It includes the common task information from the parent class,
     * followed by the deadline information in the format "(by: deadline)".
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format(" (from: %s)", eventTime);
    }
}
