package command;

import message.Message;

/**
 * Command interface for executable user commands.
 * Implementing classes should:
 *  - Contain the logic for executing a specific command
 *  - Return a user-friendly {@link Message} describing the result
 *  - Throw a {@link RuntimeException} if execution fails, which will be caught by CommandProcessor
 */
public interface Command {
    Message execute();

    /**
     * Indicates whether this command should terminate the application session.
     * Only exit-type command should return true.
     */
    default boolean isExit() {
        return false;
    }
}
