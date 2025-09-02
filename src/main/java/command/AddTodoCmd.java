package command;

import manager.TaskManager;
import message.TaskAddedMessage;
import message.ErrorMessage;
import message.Message;
import task.Task;
import task.TodoTask;

/**
 * Command to add a simple todo task without any date constraints.
 */
public class AddTodoCmd extends BaseTaskCommand {

    public AddTodoCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    @Override
    public Message execute() {
        if (args == null || args.isBlank()) {
            return new ErrorMessage(ErrorMessage.MISSING_DESCRIPTION);
        }

        String description = args.trim();

        Task todo = new TodoTask(description);
        taskManager.addTask(todo);
        return new TaskAddedMessage(todo, taskManager);
    }
}
