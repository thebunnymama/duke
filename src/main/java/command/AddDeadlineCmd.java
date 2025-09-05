package command;

import exception.InvalidDateTimeException;
import manager.TaskManager;
import message.TaskAddedMessage;
import message.ErrorMessage;
import message.Message;
import parser.DateTimeParserUtil;
import parser.ParsedDateTime;
import task.DeadlineTask;
import task.Task;


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

        // Split input: "finish task /by 11/11/2011" → ["finish task", "11/11/2011"]
        String[] tokens = args.split("\\s+/by\\s+");

        if (tokens.length != 2 || tokens[0].trim().isEmpty() || tokens[1].trim().isEmpty()) {
            return new ErrorMessage(ErrorMessage.DEADLINE_FORMAT);
        }

        String description = tokens[0].trim();
        String dateString = tokens[1].trim();

        try {
            ParsedDateTime parsed = DateTimeParserUtil.parse(dateString);
            Task deadline = new DeadlineTask(description, parsed);
            taskManager.addTask(deadline);
            return new TaskAddedMessage(deadline, taskManager);

        } catch (InvalidDateTimeException e) {
            if (e.getType() == InvalidDateTimeException.ErrorTypes.INVALID_DATETIME_VALUE) {
                return new ErrorMessage(String.format(ErrorMessage.INVALID_DATETIME_VALUE, dateString));
            }
            return new ErrorMessage(ErrorMessage.INVALID_DATETIME_FORMAT);
        }
    }
}
