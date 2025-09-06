package command;

import manager.TaskManager;
import message.ErrorMessage;
import message.TaskMarkedMessage;
import message.Message;
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
     * @return TaskMarkedMessage or TaskUnmarkedMessage on success, ErrorMessage on failure
     */
    @Override
    public Message execute() {
        if (taskManager.isEmpty()) {
            return new ErrorMessage(ErrorMessage.EMPTY_LIST);
        }

        if (args == null || args.isBlank()) {
            return new ErrorMessage(ErrorMessage.MISSING_TASK_NUMBER);
        }

        try {
            int taskNumber = Integer.parseInt(args.trim());
            Task task = taskManager.getTask(taskNumber);

            // Check if task is already in the desired state
            String state = markDone ? "done" : "unmarked";
            String undo = markDone ? "unmark" : "mark";
            if (markDone == task.isDone()) {
                return new ErrorMessage(String.format(ErrorMessage.TASK_STATE, state, undo, taskNumber));
            }

            // Perform the update
            if (markDone) {
                taskManager.markTaskDone(taskNumber);
                return new TaskMarkedMessage(task);
            } else {
                taskManager.unmarkTask(taskNumber);
                return new TaskUnmarkedMessage(task);
            }

        } catch (NumberFormatException e) {
            return new ErrorMessage(String.format(ErrorMessage.INVALID_NUMBER_FORMAT, args));
        } catch (IndexOutOfBoundsException e) {
            return new ErrorMessage(String.format(ErrorMessage.TASK_NOT_FOUND, args));
        }
    }
}
