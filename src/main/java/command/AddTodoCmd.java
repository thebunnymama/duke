package command;

import exception.InvalidTaskFormatException;
import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskAddedMessage;
import task.Task;
import task.TodoTask;
import util.TokenizerUtil;

import java.util.regex.Pattern;

/**
 * Command to add a simple todo task without any date constraints.
 */
public class AddTodoCmd extends BaseTaskCommand {

    private static final Pattern TODO_PATTERN = Pattern.compile("(.+)");

    public AddTodoCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Creates a todo task with the provided String input.
     *
     * @return a {@link TaskAddedMessage} on success, {@link ErrorMessage} if no description is provided
     */
    @Override
    public Message execute() {
        try {
            String[] tokens = TokenizerUtil.tokenize(
                    args, TODO_PATTERN, 1, InvalidTaskFormatException.ErrorType.MISSING_DESCRIPTION
            );
            String description = tokens[0];
            Task todo = new TodoTask(description);
            taskManager.addTask(todo);
            return new TaskAddedMessage(todo, taskManager);
        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
