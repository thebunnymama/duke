package exception;

import message.ErrorMessage;

/**
 * Exception thrown when task operations encounter invalid states, inputs, or conditions.
 * <p>This exception extends {@link MeeBotException} and categorizes task-related operation
 * failures into specific types by converting itself to appropriate {@link ErrorMessage} objects.</p>
 *
 * @see MeeBotException
 * @see ErrorMessage
 */
public class InvalidTaskOperationException extends MeeBotException {

    /**
     * Enumeration of possible task operation error types.
     * Each error type represents a different category of task operation failure,
     * allowing for specific error handling and appropriate user feedback.
     */
    public enum ErrorType {
        EMPTY_LIST,
        MISSING_TASK_NUMBER,
        INVALID_NUMBER_FORMAT,
        TASK_NOT_FOUND,
        TASK_STATE
    }

    private final ErrorType type;
    private final Object[] inputs;

    public InvalidTaskOperationException(ErrorType type, Object... inputs) {
        super(inputs != null && inputs.length > 0 ? inputs[0].toString() : null);
        this.type = type;
        this.inputs = inputs;
    }

    /**
     * Converts this exception to an appropriate ErrorMessage for user display.
     * <p>This method maps the error type to a formatted error message using the
     * provided inputs. The specific message format depends on the error type
     * and is defined in the {@link ErrorMessage} class constants.
     *
     * @return an {@link ErrorMessage} containing a formatted error for display to end users
     */
    @Override
    public ErrorMessage toErrorMessage() {
        return switch (type) {
            case EMPTY_LIST -> new ErrorMessage(ErrorMessage.EMPTY_LIST);
            case MISSING_TASK_NUMBER -> new ErrorMessage(ErrorMessage.MISSING_TASK_NUMBER);
            case INVALID_NUMBER_FORMAT -> new ErrorMessage(String.format(ErrorMessage.INVALID_NUMBER_FORMAT, inputs));
            case TASK_NOT_FOUND -> new ErrorMessage(String.format(ErrorMessage.TASK_NOT_FOUND, inputs));
            case TASK_STATE -> new ErrorMessage(String.format(ErrorMessage.TASK_STATE, inputs));
        };
    }
}
