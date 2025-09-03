package task;

import parser.DateTimeUtil;
import parser.ParsedDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Task with a specific date/time.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private final boolean hasTime;

    public DeadlineTask(String description, ParsedDateTime pdt) {
        super(description);
        this.deadline = pdt.getDateTime();
        this.hasTime = pdt.hasTime();
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
        String deadlineString = hasTime
                ? DateTimeUtil.format(deadline)
                : DateTimeUtil.formatDateOnly(deadline.toLocalDate());

        return super.toString() +
                String.format(" (by: %s)", deadlineString);
    }
}
