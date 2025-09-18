package command;

import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskDeletedMessage;
import task.Task;
import util.TaskIndexParser;

/**
 * Command to remove a task from the task list, regardless of its completion status.
 */
public class DeleteTaskCmd extends BaseTaskCommand {

    public DeleteTaskCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Validates user input and removes the specified task from the list.
     *
     * @return {@link TaskDeletedMessage} on success, {@link ErrorMessage} on invalid input or task not found
     */
    @Override
    public Message execute() {
        try{
            int taskNumber = TaskIndexParser.parseTaskIndex(args, taskManager);
            Task task = taskManager.getTask(taskNumber);
            taskManager.deleteTask(taskNumber);
            return new TaskDeletedMessage(task, taskManager);
        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
