package command;

import manager.TaskManager;
import message.ErrorMessage;

/**
 * Base class providing common functionality for validating input for adding task commands
 */
public abstract class AddTaskCommand implements Command {
    protected final TaskManager taskManager;

    public AddTaskCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
}
