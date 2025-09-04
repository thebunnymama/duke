package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing various date and time String inputs into LocalDateTime objects.
 */
public class DateTimeUtil {

    /**
     * Enum representing supported date/time input patterns.
     * Reference on how to nest enum in another class:
     * <a href="https://www.tutorialspoint.com/can-we-define-an-enum-inside-a-class-in-java">...</a>
     */
    private enum DateTimePattern {
        // Date with 24-hour format time patterns
        LONG_DMYHM("d MMM yyyy HHmm", true),    // 9 Sep 2025 2210
        SLASH_DMYHM("d/M/yyyy HHmm", true),     // 9/9/2025 2210
        DASH_DMYHM("d-M-yyyy HHmm", true),      // 9-9-2025 2210
        ISO_DATE_TIME("yyyy-MM-dd HHmm", true), // 2025-09-09 2210

        // Date only patterns
        LONG_DMY("dd MMM yyyy", false),         // 9 Sep 2025
        SLASH_DMY("d/M/yyyy", false),           // 9/9/2025
        DASH_DMY("d-M-yyyy", false),            // 9-9-2025
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


    /* Date-time display format e.g. 09 Sep 2025, 10:10 PM */
    private static final DateTimeFormatter DISPLAY_DATETIME =
        DateTimeFormatter.ofPattern("dd MMM yyyy, h:mm a");

    /* Date-only display format e.g. 09 Sep 2025 */
    private static final DateTimeFormatter DISPLAY_DATE =
            DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Format LocalDateTime to String
     */
    public static String format(LocalDateTime dt) {
        return dt.format(DISPLAY_DATETIME);
    }

    /**
     * Format LocalDate to String
     */
    public static String formatDateOnly(LocalDate date) {
        return date.format(DISPLAY_DATE);
    }

    /**
     * Parses a date/time string into a LocalDateTime object using known patterns
     *
     * @param dateTimeString the date/time string to parse
     * @return ParsedDateTime object containing the parsed LocalDateTime and time metadata
     * @throws DateTimeParseException if the input does not match any supported format
     */
    public static ParsedDateTime parse(String dateTimeString) throws DateTimeParseException {
        for (DateTimePattern p : DateTimePattern.values()) {
            try {
                if (p.hasTime()) {
                    LocalDateTime dt = LocalDateTime.parse(dateTimeString, p.getFormatter());
                    return new ParsedDateTime(dt, true);
                } else {
                    LocalDate date = LocalDate.parse(dateTimeString, p.getFormatter());
                    return new ParsedDateTime(date.atStartOfDay(), false);
                }
            } catch (DateTimeParseException ignored) {}   // try next formatter
        }
        throw new DateTimeParseException("Unsupported format: " + dateTimeString, dateTimeString, 0);
    }
}
