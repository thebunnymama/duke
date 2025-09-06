package command;

import exception.InvalidDateTimeException;
import manager.TaskManager;
import message.TaskAddedMessage;
import message.ErrorMessage;
import message.Message;
import parser.DateTimeParserUtil;
import parser.ParsedDateTime;
import task.EventTask;
import task.Task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command to add a new event task with a time period.
 */
public class AddEventCmd extends BaseTaskCommand {

    /**
     * Lazy regex to stop at first /from and /to
     * Regex syntax generated with the help of ChatGPT.
     */
    private static final Pattern EVENT_PATTERN = Pattern.compile(
            "(?<description>.+?)\\s*/from\\s*(?<start>.+?)\\s*/to\\s*(?<end>.+)"
    );

    public AddEventCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Creates an event task from String input: "description /from dateTime /to dateTime"
     *
     * @return TaskAddedMessage on success, ErrorMessage on invalid format
     */
    @Override
    public Message execute() {
        if (args == null || args.isBlank()) {
            return new ErrorMessage(ErrorMessage.MISSING_DESCRIPTION);
        }

        // Expect format: <description> /from <startTime> /to <endTime>
        Matcher matcher = EVENT_PATTERN.matcher(args.trim());

        if (!matcher.matches()
                || matcher.group("description").trim().isEmpty()
                || matcher.group("start").trim().isEmpty()
                || matcher.group("end").trim().isEmpty()) {
            return new ErrorMessage(ErrorMessage.EVENT_FORMAT);
        }

        String description = matcher.group("description").trim();
        String startString = matcher.group("start").trim();
        String endString = matcher.group("end").trim();

        try {
            ParsedDateTime start = DateTimeParserUtil.parse(startString);
            ParsedDateTime end = DateTimeParserUtil.parse(endString);

            if (!start.dateTime().isBefore(end.dateTime())) {
                return new ErrorMessage(ErrorMessage.END_BEFORE_START);
            }

            Task event = new EventTask(description, start, end);
            taskManager.addTask(event);
            return new TaskAddedMessage(event, taskManager);

        } catch (InvalidDateTimeException e) {
            if (e.getType() == InvalidDateTimeException.ErrorTypes.INVALID_DATETIME_VALUE) {
                return new ErrorMessage(String.format(
                        ErrorMessage.INVALID_DATETIME_VALUE,
                        startString + " and/or " + endString));
            }
            return new ErrorMessage(ErrorMessage.INVALID_DATETIME_FORMAT);
        }
    }
}
