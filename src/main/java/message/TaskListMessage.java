package message;

import manager.TaskManager;
import task.Task;

import java.util.List;

public class TaskListMessage implements Message {
    private final TaskManager taskManager;

    public TaskListMessage(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public String getMessage() {
        List<Task> tasks = taskManager.getTask();
        if (tasks.isEmpty()) {
            return "You have no task";
        }

        StringBuilder result = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(". ").append(tasks.get(i).getDescription()).append("\n");
        }
        return result.toString().trim();

    }
}
