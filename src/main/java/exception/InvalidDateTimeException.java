package exception;

import message.ErrorMessage;

/**
 * Custom exception for date/time parsing failures with detailed error classification.
 * Provides structured error information for better user experience.
 */
public class InvalidDateTimeException extends MeeBotException {

    /**
     * Enumeration of specific date/time parsing error types.
     */
    public enum ErrorType {
        UNSUPPORTED_FORMAT,
        INVALID_DATETIME_VALUE,
        END_BEFORE_START;
    }

    private final ErrorType type;
    private final String input;

    public InvalidDateTimeException(ErrorType type, String input, Throwable cause) {
        super(input, cause);
        this.type = type;
        this.input = input;
    }

    public InvalidDateTimeException(ErrorType type, String input) {
        this(type, input, null);
    }

    /**
     * Converts exception to a user-friendly error message. The returned message is mapped
     * from the error type to an appropriate standardized error message.
     *
     * @return an {@link ErrorMessage} containing a formatted error for display to end users
     */
    @Override
    public ErrorMessage toErrorMessage() {
        return switch (type) {
            case INVALID_DATETIME_VALUE -> new ErrorMessage(
                    String.format(ErrorMessage.INVALID_DATETIME_VALUE, input)
            );
            case UNSUPPORTED_FORMAT -> new ErrorMessage(ErrorMessage.INVALID_DATETIME_FORMAT);
            case END_BEFORE_START -> new ErrorMessage(ErrorMessage.END_BEFORE_START);
        };
    }
}
