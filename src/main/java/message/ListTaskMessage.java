package message;

import manager.TaskManager;
import task.Task;

import java.util.List;

/**
 * Dynamic task listing with 1-based numbering for user display.
 */
public class ListTaskMessage implements Message {
    private final TaskManager taskManager;

    public ListTaskMessage(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Returns a formatted numbered task list or empty list message if no tasks exist.
     */
    @Override
    public String message() {
        List<Task> tasks = taskManager.getAllTask();

        // Builds numbered task list with 1-based indexing
        StringBuilder content = new StringBuilder("Here's your current mee-x of responsibilities:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            content.append(String.format("%d. %s\n", i + 1, task.toString()));
        }
        return content.toString().trim();
    }
}
