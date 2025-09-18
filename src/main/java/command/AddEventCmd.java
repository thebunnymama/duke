package command;

import exception.InvalidTaskFormatException.ErrorType;
import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskAddedMessage;
import task.EventTask;
import task.Task;
import util.DateTimeParser;
import util.ParsedDateTime;
import util.TokenizerUtil;

import java.util.regex.Pattern;

/**
 * Command to add a new event task with a time period.
 */
public class AddEventCmd extends BaseTaskCommand {

    /* Lazy regex syntax generated with the help of ChatGPT */
    private static final Pattern EVENT_PATTERN = Pattern.compile(
            "(.+?)\\s*/from\\s*(.+?)\\s*/to\\s*(.+)",
            Pattern.CASE_INSENSITIVE
    );

    public AddEventCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Creates an event task from String input: "description /from dateTime /to dateTime"
     *
     * @return {@link TaskAddedMessage} on success, {@link ErrorMessage} on invalid format
     */
    @Override
    public Message execute() {
        try {
            // Split input: "attend event /from 11/11/2011 /to 12/11/2011" → ["attend event", "11/11/2011", "12/11/2011"]
            String[] tokens = TokenizerUtil.tokenize(
                    args, EVENT_PATTERN, 3, ErrorType.EVENT
            );

            String description = tokens[0];
            String startString = tokens[1];
            String endString = tokens[2];

            ParsedDateTime start = DateTimeParser.parse(startString);
            ParsedDateTime end = DateTimeParser.parse(endString);
            Task event = new EventTask(description, start, end);
            taskManager.addTask(event);
            return new TaskAddedMessage(event, taskManager);

        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
