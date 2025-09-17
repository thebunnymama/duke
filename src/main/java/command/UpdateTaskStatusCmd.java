package command;

import exception.InvalidTaskOperationException;
import exception.InvalidTaskOperationException.ErrorType;
import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskMarkedMessage;
import message.TaskUnmarkedMessage;
import task.Task;

/**
 * Command to mark or unmark a task as done by task number.
 */
public class UpdateTaskStatusCmd extends BaseTaskCommand {
    private final boolean markDone;

    public UpdateTaskStatusCmd(TaskManager taskManager, String args, boolean markDone) {
        super(taskManager, args);
        this.markDone = markDone;
    }

    /**
     * Validates input, checks current state to prevent redundant operations, and
     * updates the completion status of the specified task.
     *
     * @return {@link TaskMarkedMessage} or {@link TaskUnmarkedMessage} on success, {@link ErrorMessage} on failure
     */
    @Override
    public Message execute() {
        try {
            if (taskManager.isEmpty()) {
                return new ErrorMessage(ErrorMessage.EMPTY_LIST);
            }

            if (args == null || args.isBlank()) {
                return new ErrorMessage(ErrorMessage.MISSING_TASK_NUMBER);
            }

            int taskNumber;
            try {
                taskNumber = Integer.parseInt(args.trim());
            } catch (NumberFormatException e) {
                throw new InvalidTaskOperationException(ErrorType.INVALID_NUMBER_FORMAT, args);
            }

            Task task;
            if (markDone) {
                taskManager.markTaskDone(taskNumber);
                task = taskManager.getTask(taskNumber);
                return new TaskMarkedMessage(task);
            } else {
                taskManager.unmarkTask(taskNumber);
                task = taskManager.getTask(taskNumber);
                return new TaskUnmarkedMessage(task);
            }
        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
