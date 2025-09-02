package command;

import manager.TaskManager;

/**
 * Base class for commands that operate on tasks through TaskManager.
 * Provides common TaskManager dependency injection for task-related operations.
 */
public abstract class BaseTaskCommand implements Command {
    protected final TaskManager taskManager;

    public BaseTaskCommand(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
}
