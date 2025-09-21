package task;

import util.ParsedDateTime;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Task with a specific date/time.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private final boolean hasTime;

    public DeadlineTask(String description, ParsedDateTime pdt) {
        super(description);
        this.deadline = pdt.dateTime();
        this.hasTime = pdt.hasTime();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    @Override
    public List<LocalDateTime> getDates() {
        return Collections.singletonList(deadline);
    }

    @Override
    public String toJsonFields() {
        String deadlineStr = hasTime
                ? deadline.toString()
                : deadline.toLocalDate().toString();
        return String.format(",\n  \"deadline\":\"%s\"", deadlineStr);
    }

    @Override
    public Task copy() {
        ParsedDateTime pdt = new ParsedDateTime(this.deadline, this.hasTime);
        return new DeadlineTask(this.getDescription(), pdt);
    }

    /**
     * Extends base format with deadline information in the format "(by: deadline)".
     */
    @Override
    public String toString() {
        String deadlineString = ParsedDateTime.format(deadline, hasTime);
        return super.toString() +
                String.format(" (by: %s)", deadlineString);
    }
}
