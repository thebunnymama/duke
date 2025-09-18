package command;

import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskMarkedMessage;
import message.TaskUnmarkedMessage;
import task.Task;
import util.TaskIndexParser;

/**
 * Command to update the completion status of a task by its index.
 * This command can either mark a task as completed or mark it as pending,
 * depending on the {@code markDone} parameter provided during construction.
 * The command validates the task index and prevents redundant status changes.
 *
 * @see TaskManager#markTaskDone(int)
 * @see TaskManager#unmarkTask(int)
 */
public class UpdateTaskStatusCmd extends BaseTaskCommand {
    private final boolean markDone;

    public UpdateTaskStatusCmd(TaskManager taskManager, String args, boolean markDone) {
        super(taskManager, args);
        this.markDone = markDone;
    }

    /**
     * Validates input, task existence, checks current state to prevent redundant
     * operations, and updates the completion status of the specified task.
     *
     * @return {@link TaskMarkedMessage} on successful mark done, or
     *         {@link TaskUnmarkedMessage} on successful unmark, or
     *         {@link ErrorMessage} if validation fails or task doesn't exist
     */
    @Override
    public Message execute() {
        try {
            int taskNumber = TaskIndexParser.parseTaskIndex(args, taskManager);
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
