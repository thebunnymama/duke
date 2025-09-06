package parser;

import exception.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing various date and time String inputs into LocalDateTime objects.
 */
public final class DateTimeParserUtil {
    /**
     * Enum representing supported date/time input patterns.
     * Reference on how to nest enum in another class:
     * <a href="https://www.tutorialspoint.com/can-we-define-an-enum-inside-a-class-in-java">...</a>
     */
    private enum DateTimePattern {
        // Date with 24-hour time patterns
        SLASH_DMYHM("d/M/yyyy HHmm", true),     // 9/9/2025 2210
        DASH_DMYHM("d-M-yyyy HHmm", true),      // 9-9-2025 2210
        LONG_DMYHM("d MMM yyyy HHmm", true),    // 9 Sep 2025 2210
        ISO_DATE_TIME("yyyy-MM-dd HHmm", true), // 2025-09-09 2210

        // Date only patterns
        SLASH_DMY("d/M/yyyy", false),           // 9/9/2025
        DASH_DMY("d-M-yyyy", false),            // 9-9-2025
        LONG_DMY("d MMM yyyy", false),         // 9 Sep 2025
        ISO_DATE("yyyy-MM-dd", false);          // 2025-09-09

        private final DateTimeFormatter formatter;
        private final boolean hasTime;

        DateTimePattern(String pattern, boolean hasTime) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
            this.hasTime = hasTime;
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }

        public boolean hasTime() {
            return hasTime;
        }
    }

    /* Private constructor to prevent instantiation */
    private DateTimeParserUtil() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Parses a date/time string into a LocalDateTime object using known patterns
     * If time is omitted from the input, it defaults to 00:00 (midnight)
     *
     * @param dateTimeString the date/time string to parse
     * @return ParsedDateTime object containing the parsed LocalDateTime and time metadata
     * @throws DateTimeParseException if the input does not match any supported format
     */
    public static ParsedDateTime parse(String dateTimeString) throws DateTimeParseException {
        DateTimeParseException exception = null;

        for (DateTimePattern p : DateTimePattern.values()) {
            try {
                if (p.hasTime()) {
                    LocalDateTime dt = LocalDateTime.parse(dateTimeString, p.getFormatter());
                    return new ParsedDateTime(dt, true);
                } else {
                    LocalDate date = LocalDate.parse(dateTimeString, p.getFormatter());
                    return new ParsedDateTime(date.atStartOfDay(), false);
                }
            } catch (DateTimeParseException e) {
                exception = e;
                InvalidDateTimeException ex = classifyFailure(dateTimeString, e);
                if (ex.getType() != InvalidDateTimeException.ErrorTypes.UNSUPPORTED_FORMAT) {
                    throw ex;   // early exit for INVALID_DATETIME_VALUE
                }
            }
        }
        throw classifyFailure(dateTimeString, exception);
    }

    /**
     * Classifies a DateTimeParseException failure and creates an appropriate InvalidDateTimeException.
     *
     * @param input the input date/time string that failed to parse
     * @param cause the original DateTimeParseException (can be null)
     * @return a new InvalidDateTimeException with appropriate classification
     */
    private static InvalidDateTimeException classifyFailure(String input, DateTimeParseException cause) {
        InvalidDateTimeException.ErrorTypes type = InvalidDateTimeException.ErrorTypes.classify(cause.getMessage());
        return new InvalidDateTimeException(type, input, cause);
    }
}
