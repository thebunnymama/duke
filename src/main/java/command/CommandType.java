package command;

import manager.TaskManager;

import java.util.function.BiFunction;

/**
 * Enum representing all available commands with their factories and help text.
 * Combines command registration, help documentation, and command creation in a single, maintainable location.
 */
public enum CommandType {
    LIST("list", "Displays all tasks",
            (tm, args) -> new ListCmd(tm)),

    TODO("todo", "Adds todo. " +
            "Format: todo <description>",
            AddTodoCmd::new),

    DEADLINE("deadline", "Adds deadline. " +
            "Format: deadline <description> /by <date>",
            AddDeadlineCmd::new),

    EVENT("event", "Adds event. " +
            "Format: event <description> /from <date>",
            AddEventCmd::new),

    DELETE("delete", "Deletes a task by number. " +
            "Format: delete <index>",
            DeleteTaskCmd::new),

    MARK ("mark", "Marks a task as done. " +
            "Format: mark <index>",
            (tm, args) -> new UpdateTaskStatusCmd(tm, args, true)),

    UNMARK("unmark", "Marks a task as pending. " +
            "Format: unmark <index>",
                (tm, args) -> new UpdateTaskStatusCmd(tm, args, false)),

    HELP("help", "Display help instructions",
            (tm, args) -> new HelpCmd()),

    BYE("bye", "Exits the chatbot",
            (tm, args) -> new ExitCmd());

    private final String keyword;
    private final String helpText;
    private final BiFunction<TaskManager, String, Command> cmdFactory;

    CommandType(String keyword, String helpText, BiFunction<TaskManager, String, Command> factory) {
        this.keyword = keyword;
        this.helpText = helpText;
        this.cmdFactory = factory;
    }

    public String getKeyword() {
        return keyword;
    }

    /**
     * Maps user input to CommandType enum (case-insensitive).
     *
     * @param keyword the user input command to look up
     * @return matching CommandType if found or null if not found
     */
    public static CommandType fromKeyword(String keyword) {
        for (CommandType type : values()) {
            if (type.keyword.equalsIgnoreCase(keyword.trim())) {
                return type;
            }
        }
        return null; // Not found
    }

    public String getHelpText() {
        return helpText;
    }

    /**
     * Factory method to create appropriate Command instance.
     *
     * @param tm the task manager for command execution
     * @param args parsed command arguments
     * @return executable Command object
     */
    public Command createCommand(TaskManager tm, String args) {
        return cmdFactory.apply(tm, args);
    }
}
