package message;

import task.Task;

/**
 * Message displayed when a completed task is marked as pending (unmarked).
 */
public class TaskUnmarkedMessage implements Message {
    private final Task task;
    private final boolean showSortWarning;

    public TaskUnmarkedMessage(Task task, boolean showSortWarning) {
        this.task = task;
        this.showSortWarning = showSortWarning;
    }

    @Override
    public String message() {
        String warning = """
                
                Mee-ssage: list may not be sorted now, sort again to put it in order.
                """;

        return String.format("""
                        '%s' is back to pending - just like when hawker uncle changes his mind about closing time.%s
                        """,
                task.toString(),
                showSortWarning
                        ? warning
                        : ""
        );
    }
}
