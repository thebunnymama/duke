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

    /**
     * Parse task type from string input (case-insensitive)
     */
    public static TaskType fromString(String input) {
        for (TaskType type : TaskType.values()) {
            if (type.name().equalsIgnoreCase(input))
                return type;
        }
        throw new IllegalArgumentException("Unknown task type: " + input);
    }
}
