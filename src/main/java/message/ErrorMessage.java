package message;

public class ErrorMessage implements Message {
    private final String message;

    public ErrorMessage (String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
