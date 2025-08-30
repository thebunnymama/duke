package command;

import message.Message;

/**
 * Represents an executable user command that produces a message result.
 * Commands encapsulate all logic needed to process a specific user action.
 */
public interface Command {

    /**
     * Executes this command and returns a message describing the result.
     *
     * @return Message object containing user-friendly feedback about the command execution
     * @throws RuntimeException if command execution fails (will be caught by CommandProcessor)
     */
    Message execute();

    /**
     * Indicates whether this command should terminate the application session.
     * Only exit-type command should return true.
     */
    default boolean isExit() {
        return false;
    }
}
