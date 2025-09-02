package command;

import manager.TaskManager;
import message.TaskAddedMessage;
import message.ErrorMessage;
import message.Message;
import task.EventTask;
import task.Task;

/**
 * Command to add a new event task with a time period.
 */
public class AddEventCmd extends BaseTaskCommand {
    private final String args;

    public AddEventCmd(TaskManager taskManager, String args) {
        super(taskManager);
        this.args = args;
    }

    @Override
    public Message execute() {
        if (args.isBlank()) {
            return new ErrorMessage("Error: Task description cannot be empty.");
        }

        String trimmed = args.trim();
        String[] parts = trimmed.split("\\s*/from\\s*", 2);

        // Expect format: <description> /from <dateTime to dateTime>
        if (parts.length < 2 ||
                parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return new ErrorMessage("Invalid format.\nEvent requires: <description> /from <start to end>");
        }

        String description = parts[0].trim();
        String dateString = parts[1].trim();

        Task event = new EventTask(description, dateString);
        taskManager.addTask(event);
        return new TaskAddedMessage(event, taskManager);
    }
}
