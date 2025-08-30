package command;

import manager.TaskManager;
import message.AddTaskMessage;
import message.ErrorMessage;
import message.Message;
import task.DeadlineTask;
import task.Task;

/**
 * Command to add a new deadline task with a due date.
 */
public class AddDeadlineCmd extends AddTaskCommand {
    private final String args;

    public AddDeadlineCmd(TaskManager taskManager, String args) {
        super(taskManager);
        this.args = args;
    }

    @Override
    public Message execute() {
        if (args.isBlank()) {
            return new ErrorMessage("Error: Task description cannot be empty.");
        }

        String trimmed = args.trim();
        String[] parts = trimmed.split("\\s*/by\\s*", 2);

        // Expect format: <description> /by <dateTime>
        if (parts.length < 2 ||
                parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return new ErrorMessage("Invalid format. Deadline requires: <description> /by <date>");
        }

        String description = parts[0].trim();
        String dateString = parts[1].trim();

        Task deadline = new DeadlineTask(description, dateString);
        taskManager.addTask(deadline);
        return new AddTaskMessage(deadline, taskManager);
    }
}
