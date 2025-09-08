package command;

import message.Message;

/**
 * Command interface for executable user commands.
 * </p>Implementing classes should:
 * <li>Contain the logic for executing a specific command</li>
 * <li>Return a user-friendly {@link Message} describing the result</li>
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
