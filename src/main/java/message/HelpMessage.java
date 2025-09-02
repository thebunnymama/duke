package message;

import command.Command;
import command.CommandType;

public class HelpMessage implements Message {
    private final CommandType[] commands;

    public HelpMessage(CommandType[] commands) {
        this.commands = commands;
    }

    /**
     * Generates a formatted help text showing available commands and their descriptions.
     * Excludes HELP command to prevent recursion.
     */
    @Override
    public String getMessage() {
        StringBuilder content = new StringBuilder("Here's what mee can do for you:\n");

        int maxKeywordLength = 0;
        for (CommandType command : commands) {
            maxKeywordLength = Math.max(maxKeywordLength, command.getKeyword().length());
        }
        int indent = maxKeywordLength + 3;

        for (CommandType command : commands) {
            // Skip display for help command
            if (command == CommandType.HELP) continue;

            String keyword = command.getKeyword();
            String helpText = command.getHelpText();

            content.append(String.format("%-" + maxKeywordLength + "s : %s\n",
                    keyword, helpText));
        }
        return content.toString();
    }
}
