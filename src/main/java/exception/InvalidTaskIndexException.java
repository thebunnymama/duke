package exception;

public class InvalidTaskIndexException extends RuntimeException {
    public InvalidTaskIndexException() {
        super("Invalid task index: ");
    }
}
