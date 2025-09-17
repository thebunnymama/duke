package util;

import command.*;
import manager.TaskManager;
import message.ErrorMessage;

/**
 * Central processor for parsing user commands into executable Command objects.
 * <p>Responsibilities:
 * <li>Parse raw user input into command name and arguments</li>
 * <li>Look up appropriate command based on command name</li>
 * <li>Handle parsing errors gracefully</li>
 */
public final class CommandProcessor {
    private final TaskManager taskManager;

    /**
     * Creates a new CommandProcessor with access to the task management system.
     *
     * @param taskManager the task manager for command execution, must not be null
     */
    public CommandProcessor(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Splits user input on first whitespace to separate command from arguments.
     *
     * @param input raw user input string
     * @return Command object ready to execute, or {@link ErrorMessage} if parsing fails
     */
    public Command parseCommand(String input) {
        if (input == null || input.isBlank()) {
            return () -> new ErrorMessage(String.format(ErrorMessage.INVALID_COMMAND_KEYWORD, input));
        }

        // Split input: "deadline finish task /by 11/11/2011" → ["deadline", "finish task /by 11/11/2011"]
        String[] tokens = input.trim().split("\\s+", 2);
        String command = tokens[0].toLowerCase();
        String args = tokens.length > 1 ? tokens[1] : "";

        CommandType cmdType = CommandType.fromKeyword(command);

        if (cmdType == null) {
            return () -> new ErrorMessage(String.format(ErrorMessage.INVALID_COMMAND_KEYWORD, command));
        }
        return cmdType.createCommand(taskManager, args);
    }
}
