package message;

public class WelcomeMessage implements Message {

        private static final String WELCOME_CONTENT =
            "Hello, I'm MeeBot.\n" +
            "Ready to mee-t your needs today!\n" +
            "What can mee stir up for you today?";

    @Override
    public String getMessage() {
        return WELCOME_CONTENT;
    }

}
