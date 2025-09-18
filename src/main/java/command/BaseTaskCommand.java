package command;

import manager.TaskManager;

/**
 * Base class for commands that operate on tasks.
 * <p>Provides common dependency injection for {@link TaskManager} and command arguments
 * to all task-related command implementations. Subclasses should focus on implementing
 * the specific command logic in their {@link #execute()} method.
 */
public abstract class BaseTaskCommand implements Command {
    protected final TaskManager taskManager;
    protected final String args;

    public BaseTaskCommand(TaskManager taskManager, String args) {
        this.taskManager = taskManager;
        this.args = args;
    }
}
