package command;

import exception.InvalidTaskFormatException.ErrorType;
import exception.MeeBotException;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import message.TaskAddedMessage;
import task.DeadlineTask;
import task.Task;
import util.DateTimeParser;
import util.ParsedDateTime;
import util.TokenizerUtil;

import java.util.regex.Pattern;


/**
 * Command to add a new deadline task with a due date.
 */
public class AddDeadlineCmd extends BaseTaskCommand {

    /* Lazy regex syntax generated with the help of ChatGPT */
    private static final Pattern DEADLINE_PATTERN = Pattern.compile(
            "(.+?)\\s*/by\\s*(.+)",
            Pattern.CASE_INSENSITIVE
    );

    public AddDeadlineCmd(TaskManager taskManager, String args) {
        super(taskManager, args);
    }

    /**
     * Creates a deadline task from String input: "description /by dateTime"
     *
     * @return {@link TaskAddedMessage} on success, {@link ErrorMessage} on invalid format
     */
    @Override
    public Message execute() {
        try {
            // Split input: "finish task /by 11/11/2011" → ["finish task", "11/11/2011"]
            String[] tokens = TokenizerUtil.tokenize(
                    args, DEADLINE_PATTERN, 2, ErrorType.DEADLINE
            );

            String description = tokens[0];
            String dateString = tokens[1];

            ParsedDateTime parsed = DateTimeParser.parse(dateString);
            Task deadline = new DeadlineTask(description, parsed);
            taskManager.addTask(deadline);
            return new TaskAddedMessage(deadline, taskManager);

        } catch (MeeBotException e) {
            return e.toErrorMessage();
        }
    }
}
