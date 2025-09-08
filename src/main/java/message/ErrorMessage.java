package message;

/**
 * Error message container with Singaporean-themed text constants.
 */
public record ErrorMessage(String message) implements Message {

    // Command and input errors
    public static final String INVALID_COMMAND_KEYWORD = """
            Sorry, I'm mee-xed up by '%s'. I can help with:
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
    public static final String INVALID_NUMBER_FORMAT = """
            Eh friend, '%s' is not a number lah. Task numbers are whole numbers like 1, 2, 3.
            """;
    public static final String DEADLINE_FORMAT = """
            Your command is messier than mee goreng!
            Deadline needs a description and '/by' date.
            Try: deadline submit report /by 1/11/2025
            Or : deadline submit report /by 1/11/2025 2359
            """;
    public static final String EVENT_FORMAT = """
            Your command is messier than mee goreng!
            Event needs a description, '/from' start and '/to' end.
            Try: event meeting /from 1/11/2025 /to 1/11/2025
            Or : event meeting /from 1/11/2025 1400 /to 1/11/2025 1500
            """;
    public static final String FILTER_FORMAT = """
            %s Pick at least 1 from these 3 criteria:
            • task:todo | deadline | event
            • done:true | false
            • date:YYYY-MM-DD
            Try: filter task:deadline
            Or : filter task:deadline & done:false
            Or : filter task:deadline & done:false & date:2024-01-15
            """;

    // Task existence and state errors
    public static final String EMPTY_LIST = """
            Your task list is emptier than kopitiam during circuit breaker. \
            Add a task before using this command!
            """;
    public static final String TASK_NOT_FOUND = """
            Task %s doesn't exist.
            Type 'list' to see what's actually in your task list lah!
            """;
    public static final String TASK_STATE = """
            Task is already %s, no point doing it again what!
            Try '%s %d' instead.
            """;

    // Date time errors
    public static final String INVALID_DATETIME_VALUE = """
            '%s' doesn't exist.
            Your date/time more fictional than Korean drama plot!
            Double check:
            • Dates : 1-31 for day, 1-12 for month
            • Time  : 0000-2359 (Use 24-hour format)
            • Format: day/month/year (not American style!)
            """;
    public static final String INVALID_DATETIME_FORMAT = """
            Incorrect date/time format, please use one of these:
            • Day/Month/Year: 1/1/2025 or 1-1-2025
            • Long format: 1 Jan 2025
            • ISO format: 2025-01-01
            • Optionally add time in 24-hour format: 0000-2359
            """;
    public static final String END_BEFORE_START = """
            End time cannot be earlier than start time. That's like eating dessert before the main course lah!
            Common fix: If you didn't specify time, it becomes 00:00.
            Make sure both dates have proper timing, or end time is after start time!
            """;
}
