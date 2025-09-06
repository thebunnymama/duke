package command;

import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.ListTaskMessage;

/**
 * Command to display all tasks in MeeBot.
 */
public class ListCmd implements Command {
    private final TaskManager taskManager;

    public ListCmd(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public Message execute() {
        if (taskManager.isEmpty()) {
            return new ErrorMessage(ErrorMessage.EMPTY_LIST);
        }

        return new ListTaskMessage(taskManager);
    }
}
