package command;

import message.HelpMessage;
import message.Message;

/**
 * Command to display help instructions for all commands in MeeBot.
 */
public class HelpCmd implements Command {

    @Override
    public Message execute() {
        return new HelpMessage(CommandType.values());
    }
}
