package task;

import util.ParsedDateTime;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Task with a start and end date/time.
 */
public class EventTask extends Task {
    private final LocalDateTime start, end;
    private final boolean hasTime;

    public EventTask(String description, ParsedDateTime start, ParsedDateTime end) {
        super(description);
        this.start = start.dateTime();
        this.end = end.dateTime();
        this.hasTime = start.hasTime() && end.hasTime();
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    @Override
    public List<LocalDateTime> getDates() {
        return Arrays.asList(start, end);
    }

    /**
     * Extends base format with event information in the format "(from: eventStart to: eventEnd)".
     */
    @Override
    public String toString() {
        String eventStart = ParsedDateTime.format(start, hasTime);
        String eventEnd = ParsedDateTime.format(end, hasTime);
        return super.toString() +
                String.format(" (from: %s to: %s)", eventStart, eventEnd);
    }
}
