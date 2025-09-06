package message;

/**
 * Static welcome message displayed when the application starts.
 */
public class WelcomeMessage implements Message {

        private static final String WELCOME_CONTENT =
                        """
                        Hello, I'm MeeBot.
                        Ready to mee-t your needs today!
                        Type 'help' to find out what can mee stir up for you today.
                        """;

    @Override
    public String message() {
        return WELCOME_CONTENT;
    }
}
