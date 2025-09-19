package command;

import manager.TaskManager;

import java.util.function.BiFunction;

/**
 * Enumeration of all available commands with their metadata and factories methods.
 * <p>This enum serves as a central registry that combines:
 * <ul>
 * <li>Command keywords for user input parsing</li>
 * <li>Help text for user documentation</li>
 * <li>Factory methods for command instantiation</li>
 * </ul><p>
 * This design ensures all command metadata is co-located and makes it easy to add
 * new commands by simply adding a new enum constant.
 */
public enum CommandType {
    LIST("list", "Display all tasks",
            (tm, args) -> new ListCmd(tm)),

    SORT("sort", "Display tasks in sorted order\n" +
            "Format: sort /by <date|status>",
            SortCmd::new),

    TODO("todo", "Add todo. " +
            "Format: todo <description>",
            AddTodoCmd::new),

    DEADLINE("deadline", "Add deadline.\n" +
            "Format: deadline <description> /by <date>",
            AddDeadlineCmd::new),

    EVENT("event", "Add event.\n" +
            "Format: event <description> /from <date>",
            AddEventCmd::new),

    DELETE("delete", "Delete a task by number. " +
            "Format: delete <index>",
            DeleteTaskCmd::new),

    FILTER("filter", "Filter tasks by one or more criteria.\n" +
            "Format: filter <task:todo|deadline|event> & <done:true|false> & <date:DD/MM/YYYY>",
            FilterCmd::new),

    MARK ("mark", "Mark a task as done. " +
            "Format: mark <index>",
            (tm, args) -> new UpdateTaskStatusCmd(tm, args, true)),

    UNMARK("unmark", "Mark a task as pending. " +
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

    public String getHelpText() {
        return helpText;
    }

    /**
     * Looks up a CommandType by keyword and perform case-insensitive matching.
     *
     * @param keyword the user input command to look up
     * @return matching CommandType if found or {@code null} if no match is found
     */
    public static CommandType fromKeyword(String keyword) {
        for (CommandType type : values()) {
            if (type.keyword.equalsIgnoreCase(keyword.trim())) {
                return type;
            }
        }
        return null; // Not found
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
