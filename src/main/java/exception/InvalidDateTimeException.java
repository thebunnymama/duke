package exception;

/**
 * Custom exception for date/time parsing failures with detailed error classification.
 * Provides structured error information for better user experience.
 */
public class InvalidDateTimeException extends Exception {

    public enum ErrorTypes {
        UNSUPPORTED_FORMAT,
        INVALID_DATETIME_VALUE
    }

    private final ErrorTypes type;

    public InvalidDateTimeException(ErrorTypes type, String input, Throwable cause) {
        super(input, cause);
        this.type = type;
    }
    public InvalidDateTimeException(ErrorTypes type, String input) {
        this(type, input, null);
    }

    public ErrorTypes getType() { return type; }

}
