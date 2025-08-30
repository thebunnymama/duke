package parser;

import command.*;
import manager.TaskManager;
import message.ErrorMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Central processor for parsing and creating user commands.
 * Responsibilities:
 * - Parse raw user input into command name and arguments
 * - Look up appropriate command based on command name
 * - Create and return executable Command objects
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
     * Parses user input and creates the appropriate Command object.
     *
     * @param input raw user input string
     * @return Command object ready to execute, or ErrorCommand if parsing fails
     */
    public Command parseCommand(String input) {
        if (input == null || input.isBlank()) {
            return () -> new ErrorMessage("Command cannot be empty.");
        }

        // Split input: "deadline finish reading /by Sunday" → ["deadline", "finish reading /by Sunday"]
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        return switch (command) {
            case "list" -> new ListCmd(taskManager);
            case "todo" -> new AddTodoCmd(taskManager, args);
            case "deadline" -> new AddDeadlineCmd(taskManager, args);
            case "event" -> new AddEventCmd(taskManager, args);
            case "mark" -> new UpdateTaskStatusCmd(taskManager, args, true);
            case "unmark" -> new UpdateTaskStatusCmd(taskManager, args, false);
            case "bye" -> new ExitCmd();
            default -> () -> new ErrorMessage("Unknown command: " + command);
        };
    }
}
