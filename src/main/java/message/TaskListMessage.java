package message;

import manager.TaskManager;
import task.Task;

import java.util.List;

/**
 * Displays a formatted list of all tasks with numbering.
 * Generates content dynamically based on current task state.
 */
public class TaskListMessage implements Message {
    private final TaskManager taskManager;

    /**
     * Creates a task list message.
     *
     * @param taskManager the task manager containing tasks to display
     */
    public TaskListMessage(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String getMessage() {
        List<Task> tasks = taskManager.getAllTask();

        if (tasks.isEmpty()) {
            return "You have no tasks";
        }

        StringBuilder result = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get[i];
            result.append(String.format("%d. %s\n", i + 1, task.getDescription()));
        }
        return result.toString().trim();

    }
}
