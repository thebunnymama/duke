package exception;

/**
 * Custom exception for date/time parsing failures with detailed error classification.
 * Provides structured error information for better user experience.
 */
public class InvalidDateTimeException extends RuntimeException{
    public enum ErrorTypes {
        UNSUPPORTED_FORMAT,
        INVALID_DATETIME_VALUE("MonthOfYear", "DayOfMonth",
                "HourOfDay", "MinuteOfHour");

        private final String[] errorTokens;

        ErrorTypes(String... tokens) {
            this.errorTokens = tokens;
        }

        public boolean matches(String errorMsg) {
            if (errorMsg == null) return false;
            for (String token : errorTokens) {
                if (errorMsg.contains(token)) {
                    return true;
                }
            }
            return false;
        }

        public static ErrorTypes classify(String errorMsg) {
            for (ErrorTypes type : values()) {
                if (type.matches(errorMsg)) {
                    return type;
                }
            }
            return UNSUPPORTED_FORMAT;
        }
    }

    private final ErrorTypes type;

    public InvalidDateTimeException(ErrorTypes type, String input, Throwable cause) {
        super();
        this.type = type;
    }

    public ErrorTypes getType() { return type; }
}
