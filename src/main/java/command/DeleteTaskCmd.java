package command;

import exception.InvalidTaskOperationException;
import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskDeletedMessage;
import task.Task;

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
        if (taskManager.isEmpty()) {
            return new ErrorMessage(ErrorMessage.EMPTY_LIST);
        }

        if (args.isBlank()) {
            return new ErrorMessage(ErrorMessage.MISSING_TASK_NUMBER);
        }

        try {
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(args.trim());
            } catch (NumberFormatException e) {
                throw new InvalidTaskOperationException(
                        InvalidTaskOperationException.ErrorType.INVALID_NUMBER_FORMAT,
                        args
                );
            }
            Task task = taskManager.getTask(taskNumber);
            taskManager.deleteTask(taskNumber);
            return new TaskDeletedMessage(task, taskManager);
        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
