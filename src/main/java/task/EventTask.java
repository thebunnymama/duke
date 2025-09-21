package task;

import exception.InvalidDateTimeException;
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
        if (end.dateTime().isBefore(start.dateTime())) {
            throw new InvalidDateTimeException(
                    InvalidDateTimeException.ErrorType.END_BEFORE_START, "");
        }
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

    @Override
    public String toJsonFields() {
        String startStr = hasTime
                ? start.toString()
                : start.toLocalDate().toString();

        String endStr = hasTime
                ? end.toString()
                : end.toLocalDate().toString();

        return String.format(",\n  \"start\":\"%s\",\n  \"end\":\"%s\"", startStr, endStr);
    }

    @Override
    public Task copy() {
        ParsedDateTime start = new ParsedDateTime(this.start, this.hasTime);
        ParsedDateTime end = new ParsedDateTime(this.end, this.hasTime);
        return new EventTask(this.getDescription(), start, end);
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
