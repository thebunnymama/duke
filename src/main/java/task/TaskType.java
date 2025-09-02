package task;

/**
 * Task type enumeration with single-character prefixes for display.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String taskPrefix;

    TaskType(String taskPrefix) {
        this.taskPrefix = taskPrefix;
    }

    public String getTaskPrefix() {
        return taskPrefix;
    }
}
