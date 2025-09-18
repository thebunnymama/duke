package exception;

import message.ErrorMessage;

/**
 * Custom exception for task format validation failures in MeeBot.
 * Provides detailed categorization of task formatting errors to enable
 * precise error reporting and user guidance.
 *
 * @see MeeBotException
 * @see ErrorMessage
 */
public class InvalidTaskFormatException extends MeeBotException {

    /**
     * Enumeration of task format parsing error types.
     * Each type represents a distinct category of parsing failure.
     */
    public enum ErrorType {
        MISSING_DESCRIPTION("Missing description after command keyword."),
        DEADLINE("Deadline format is invalid."),
        EVENT("Event format is invalid.");

        private final String context;

        ErrorType(String context) { this.context = context; }

        public String getContext() { return context; }

        /**
         * Factory method to create an InvalidTaskFormatException of this error type.
         * This provides a convenient way to create exceptions without explicitly
         * passing the error type to the constructor.
         *
         * @return a new InvalidTaskFormatException configured with this error type
         */
        public InvalidTaskFormatException createException() {
            return new InvalidTaskFormatException(this);
        }
    }

    private final ErrorType type;

    public InvalidTaskFormatException(ErrorType type) {
        super(type.getContext());
        this.type = type;
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
            case MISSING_DESCRIPTION -> new ErrorMessage(ErrorMessage.MISSING_DESCRIPTION);
            case DEADLINE -> new ErrorMessage(ErrorMessage.DEADLINE_FORMAT);
            case EVENT -> new ErrorMessage(ErrorMessage.EVENT_FORMAT);
        };
    }
}
