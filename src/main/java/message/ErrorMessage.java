package message;

/**
 * Error message container with Singaporean-themed text constants.
 */
public class ErrorMessage implements Message {

    // Basic validation errors
    public static final String INVALID_COMMAND = """
            '%s' is not on my menu. I can help with:
            todo, deadline, event, list, mark, unmark, delete.
            Type 'help' for details.
            """;
    public static final String MISSING_DESCRIPTION = """
            Your description is emptier than a hawker stall at 3am leh!
            Give mee something to work with after the command.
            """;
    public static final String MISSING_TASK_NUMBER = """
            You need to tell me the task number, don't make mee guess the number like buying 4D!
            Type 'list' to see your tasks, then use the number.
            """;
    public static final String TASK_NOT_FOUND = """
            Task %s doesn't exist.
            Type 'list' to see what's actually in your task bowl lah!
            """;
    public static final String EMPTY_LIST = """
            Your task list is emptier than kopitiam during circuit breaker. \
            Add a task before using this command!
            """;

    // Command format errors
    public static final String DEADLINE_FORMAT = """
            Your command is messier than mee goreng!
            Deadline needs both description and '/by' date.
            Try: deadline submit report /by Monday
            """;
    public static final String EVENT_FORMAT = """
            Your command is messier than mee goreng!
            Event needs a task name plus '/from' start time and '/to' end time.
            Try: event team meeting /from 11/11/2025 2pm /to 11/11/2025 3pm
            """;
    public static final String INVALID_NUMBER_FORMAT = """
            Eh friend, '%s' is not a number lah. Task numbers are whole numbers like 1, 2, 3.
            """;
    public static final String INVALID_DATETIME_FORMAT = """
            Incorrect date/time format, please use one of these:
            • Day/Month/Year: 1/1/2025 or 1-1-2025
            • Long format: 1 Jan 2025
            • ISO format: 2025-01-01
            • Optionally add time in 24-hour format: 2210
            """;

    // Task status errors
    public static final String TASK_STATE = """
            Task is already %s, no point doing it again what!
            Try '%s %d' instead.
            """;

    private final String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
