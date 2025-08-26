package message;

public class UserMessage implements Message {
    private final String input;

    public UserMessage(String input) {
        this.input = input;
    }

    @Override
    public String getMessage() {
        return input;
    }
}
