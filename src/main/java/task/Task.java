package task;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Base class for all task types.
 * Tasks are created incomplete and can be toggled between done/undone states.
 * Provides standardized string representation for display purpose.
 */
public abstract class Task {

    private final String description;
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     * Tasks are created in an incomplete state by default.
     *
     * @param description The task description (should not be null or empty)
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;    // tasks are incomplete by default
    }

    /**
     * Gets the type of task - must be implemented by subclasses
     */
    public abstract TaskType getTaskType();

    /**
     * Gets the dates for the task - must be implemented by subclasses
     */
    public abstract List<LocalDateTime> getDates();

    /**
     * Returns a string representation of date - must be implemented by subclasses
     */
    public abstract String toJsonFields();

    public String getDescription() {
        return description;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as completed.
     * Once marked as done, the task can still be unmarked if needed.
     */
    public void markAsDone() {
        isDone = true;
   }

    /**
     * Reverts a completed task back to incomplete state.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a common string representation showing task prefix, status and description for all tasks.
     * Format: "[TaskPrefix][X] Task description" for completed, "[TaskPrefix][ ] Task description" for pending.
     * Subclasses may extend this format with additional information.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getTaskType().getTaskPrefix(),
                isDone ? "X" : " ",
                description);
    }
}
