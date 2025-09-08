package exception;

public class InvalidFilterException extends Exception {

    public enum ErrorType {
        TOO_MANY_FILTERS("Too many criteria; maximum allowed is 3."),
        INVALID_FILTER_FORMAT("Invalid filter format."),
        UNKNOWN_FILTER_KEY("Unknown filter key.");

        private final String context;

        ErrorType(String context) {
            this.context = context;
        }

        public String getContext() {
            return context;
        }
    }

    private final ErrorType type;

    public InvalidFilterException(ErrorType type) {
        super();
        this.type = type;
    }

    public ErrorType getType() {
        return type;
    }
}
