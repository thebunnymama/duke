package command;

import manager.TaskManager;
import message.ErrorMessage;
import message.TaskMarkedMessage;
import message.Message;
import message.TaskUnmarkedMessage;
import task.Task;

/**
 * Command to mark or unmark a task as done.
 * It validates input, checks current state to prevent redundant operations,
 * and returns appropriate success or error messages.
 */
public class UpdateTaskStatusCmd extends BaseTaskCommand {
    private final String taskNumberInput;
    private final boolean markDone;

    public UpdateTaskStatusCmd(TaskManager taskManager, String taskNumberInput, boolean markDone) {
        super(taskManager);
        this.taskNumberInput = taskNumberInput;
        this.markDone = markDone;
    }

    @Override
    public Message execute() {
        if (taskNumberInput.isBlank()) {
            return new ErrorMessage("Mark/unmark command requires task number.\nFormat: mark/unmark <number>");
        }

        try {
            int taskNumber = Integer.parseInt(taskNumberInput.trim());
            Task task = taskManager.getTask(taskNumber);

            // Check if task is already in the desired state
            String state = markDone ? "done" : "unmarked";
            if (markDone == task.isDone()) {
                return new ErrorMessage("Task is already " + state + ":\n" + task);
            }

            // Perform the update
            if (markDone) {
                taskManager.markTaskDone(taskNumber);
                return new TaskMarkedMessage(task, taskNumber);
            } else {
                taskManager.unmarkTask(taskNumber);
                return new TaskUnmarkedMessage(task, taskNumber);
            }

        } catch (NumberFormatException e) {
            return new ErrorMessage(taskNumberInput + " is not a valid number. Please provide a valid number");
        } catch (IndexOutOfBoundsException e) {
            return new ErrorMessage("Task number " + taskNumberInput + " does not exist. Use 'list' to see available tasks.");
        }
    }
}
