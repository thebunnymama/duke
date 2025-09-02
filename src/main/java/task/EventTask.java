package task;

/**
 * Task with a start and end date/time. Time format is preserved as entered by user.
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
     * Extends base format with event information in the format "(by: deadline)".
     */
    @Override
    public String toString() {
        return super.toString() +
                String.format(" (from: %s)", eventTime);
    }
}
