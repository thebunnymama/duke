package task;

public abstract class Task {

    private String description;
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
     * Useful for cases where a completed task needs to be reopened.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation showing task prefix, status and description.
     * This information is common for all tasks.
     * Format: "[TaskPrefix][X] Task description" for completed, "[TaskPrefix][ ] Task description" for pending
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                getTaskType().getTaskPrefix(),
                isDone ? "X" : " ",
                description);
    }
}
