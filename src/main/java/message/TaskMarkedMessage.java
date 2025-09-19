package message;

import task.Task;

/**
 * Message displayed when a task is successfully marked as done.
 */
public class TaskMarkedMessage implements Message {
    private final Task task;
    private final boolean showSortWarning;

    public TaskMarkedMessage(Task task, boolean showSortWarning) {
        this.task = task;
        this.showSortWarning = showSortWarning;
    }

    @Override
    public String message() {
        String warning = """
                
                Mee-ssage: list may not be sorted now, sort again to put it in order.
                """;

        return String.format("""
                        '%s' accomplished like a true blue Singaporean - efficient and effective!
                        That's one less thing to mee-ddle with!%s
                        """,
                task.toString(),
                showSortWarning
                        ? warning
                        : ""
        );
    }
}
