package command;

import message.ErrorMessage;
import message.Message;

/**
 * Interface for executable user commands. All commands follow the Command pattern,
 * encapsulating a request as an object and providing a uniform interface for execution.
 * <p>
 * Implementing classes should:
 * <li>Process user input and validate parameters</li>
 * <li>Perform the requested operation</li>
 * <li>Return a user-friendly {@link Message} describing the result</li>
 * <li>Handle errors gracefully by returning {@link ErrorMessage} instances</li>
 */
public interface Command {
    Message execute();

    /**
     * Indicates whether this command should terminate the application session.
     * <p>The default implementation returns {@code false}. Only {@link ExitCmd} should return {@code true}.
     */
    default boolean isExit() {
        return false;
    }
}
