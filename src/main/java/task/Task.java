package task;

public class Task {
    // Represents a unit of work
    // Fields: description, isDone
    // Methods: getDescription(), getTaskStatus(), mark

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



}
