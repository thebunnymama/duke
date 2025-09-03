package command;

import manager.TaskManager;
import message.TaskAddedMessage;
import message.ErrorMessage;
import message.Message;
import parser.DateTimeUtil;
import parser.ParsedDateTime;
import task.DeadlineTask;
import task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Command to add a new deadline task with a due date.
 */
public class AddDeadlineCmd extends BaseTaskCommand {

    public AddDeadlineCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Creates a deadline task from String input: "description /by dateTime"
     *
     * @return TaskAddedMessage on success, ErrorMessage on invalid format
     */
    @Override
    public Message execute() {
        if (args == null || args.isBlank()) {
            return new ErrorMessage(ErrorMessage.MISSING_DESCRIPTION);
        }

        String trimmed = args.trim();
        String[] parts = trimmed.split("\\s*/by\\s*", 2);

        // Expect format: <description> /by <dateTime>
        if (parts.length < 2
                || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            return new ErrorMessage(ErrorMessage.DEADLINE_FORMAT);
        }

        String description = parts[0].trim();
        String dateString = parts[1].trim();

        try {
            ParsedDateTime parsed = DateTimeUtil.parse(dateString);
            Task deadline = new DeadlineTask(description, parsed);
            taskManager.addTask(deadline);
            return new TaskAddedMessage(deadline, taskManager);
        } catch (DateTimeParseException e) {
            return new ErrorMessage(String.format(ErrorMessage.INVALID_DATETIME_FORMAT));
        }
    }
}
