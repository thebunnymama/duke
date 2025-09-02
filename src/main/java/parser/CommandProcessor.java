package parser;

import command.*;
import manager.TaskManager;
import message.ErrorMessage;

import java.util.Map;


/**
 * Central processor for parsing user commands into executable Command objects.
 * Responsibilities:
 * - Parse raw user input into command name and arguments
 * - Look up appropriate command based on command name
 * - Handle parsing errors gracefully
 */
public class CommandProcessor {
    private final TaskManager taskManager;

    /**
     * Creates a new CommandProcessor with access to the task management system.
     *
     * @param taskManager the task manager for command execution
     * @throws IllegalArgumentException if taskManager is null
     */
    public CommandProcessor(TaskManager taskManager) {
        if (taskManager == null) {
            throw new IllegalArgumentException("TaskManager cannot be null");
        }
        this.taskManager = taskManager;
    }

    /**
     * Splits user input on first whitespace to separate command from arguments.
     *
     * @param input raw user input string
     * @return Command object ready to execute, or ErrorMessage if parsing fails
     */
    public Command parseCommand(String input) {
        if (input == null || input.isBlank()) {
            return () -> new ErrorMessage(String.format(ErrorMessage.INVALID_COMMAND, input));
        }

        // Split input: "deadline finish reading /by Sunday" → ["deadline", "finish reading /by Sunday"]
        String[] tokens = input.trim().split("\\s+", 2);
        String command = tokens[0].toLowerCase();
        String args = tokens.length > 1 ? tokens[1] : "";

        CommandType cmdType = CommandType.fromKeyword(command);

        if (cmdType == null) {
            return () -> new ErrorMessage(String.format(ErrorMessage.INVALID_COMMAND, command));
        }
        return cmdType.createCommand(taskManager, args);
    }
}
