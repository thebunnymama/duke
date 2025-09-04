package task;

import parser.DateTimeUtil;
import parser.ParsedDateTime;

import java.time.LocalDateTime;

/**
 * Task with a start and end date/time.
 */
public class EventTask extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final boolean hasTime;

    public EventTask(String description, ParsedDateTime start, ParsedDateTime end) {
        super(description);
        this.start = start.getDateTime();
        this.end = end.getDateTime();
        this.hasTime = start.hasTime() || end.hasTime();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Extends base format with event information in the format "(from: eventStart to: eventEnd)".
     */
    @Override
    public String toString() {
        String eventStart = hasTime
                ? DateTimeUtil.format(start)
                : DateTimeUtil.formatDateOnly(start.toLocalDate());

        String eventEnd = hasTime
                ? DateTimeUtil.format(end)
                : DateTimeUtil.formatDateOnly(end.toLocalDate());

        return super.toString() +
                String.format(" (from: %s to: %s)", eventStart, eventEnd);
    }
}
