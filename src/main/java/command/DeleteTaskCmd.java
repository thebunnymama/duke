package command;

import manager.TaskManager;
import message.*;
import task.Task;

/**
 * Command to remove a task from the task list, regardless of its completion status.
 */
public class DeleteTaskCmd extends BaseTaskCommand {
    private final String taskNumberInput;

    public DeleteTaskCmd(TaskManager taskManager, String taskNumberInput) {
        super(taskManager);
        this.taskNumberInput = taskNumberInput;
    }

    @Override
    public Message execute() {
        if (taskNumberInput.isBlank()) {
            return new ErrorMessage("Delete command requires task number.\nFormat: delete <number>");
        }

        try {
            int taskNumber = Integer.parseInt(taskNumberInput.trim());
            Task task = taskManager.getTask(taskNumber);

            // Perform the deletion
            taskManager.deleteTask(taskNumber);
            return new TaskDeletedMessage(task, taskManager);

        } catch (NumberFormatException e) {
            return new ErrorMessage(taskNumberInput + " is not a valid number. Please provide a valid number");
        } catch (IndexOutOfBoundsException e) {
            return new ErrorMessage("Task number " + taskNumberInput + " does not exist. Use 'list' to see available tasks.");
        }
    }
}
