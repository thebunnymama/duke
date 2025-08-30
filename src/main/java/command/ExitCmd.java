package command;

import message.GoodbyeMessage;
import message.Message;

/**
 * Command to terminate the application session.
 * When executed, a goodbye message is returned and signals that the app
 * should exit by returning a true from isExit().
 */
public class ExitCmd implements Command {

    @Override
    public Message execute() {
        return new GoodbyeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
