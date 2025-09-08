package parser;

import exception.InvalidFilterException;
import exception.InvalidDateTimeException;
import task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Factory for creating task filter predicates from string arguments.
 * Supports filtering by task type, completion status, and date.
 */
public final class TaskFilterFactory {
    private TaskFilterFactory() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Parses filter arguments into a combined predicate.
     *
     * @param args '&' delimited filter criteria (e.g. "task:deadline & done:true")
     * @return combined predicate that applies all filters with AND logic
     * @throws InvalidFilterException if filter format is invalid
     */
    public static Predicate<Task> chainPredicate(String args) throws InvalidFilterException, InvalidDateTimeException {
        // "task:deadline & done:true & date:2024-01-15" → ["task:deadline", "done:true", "date:2024-01-15"]
        String[] tokens = args.trim().split("\\s*&\\s*");
        if (tokens.length > 3) {
            throw new InvalidFilterException(InvalidFilterException.ErrorType.TOO_MANY_FILTERS);
        }

        List<Predicate<Task>> predicates = new ArrayList<>();
        for (String token : tokens) {
            predicates.add(parseFilterToken(token));
        }
        // Chain all predicates with AND logic
        Predicate<Task> chained = task -> true;
        for (Predicate<Task> p : predicates) {
            chained = chained.and(p);
        }
        return chained;
    }

    /**
     * Parses a raw filter expressions (key:value) into a predicate.
     *
     * @param token filter token in format "key:value"
     * @return predicate for the specified filter criteria
     * @throws InvalidFilterException if token format is invalid
     */
    public static Predicate<Task> parseFilterToken(String token) throws InvalidFilterException, InvalidDateTimeException {
        // "task:deadline" → ["task", "deadline"]
        String[] parts = token.split(":");
        String key = parts[0].trim().toLowerCase();
        String value = parts.length > 1 ? parts[1].trim() : "";

        if (parts.length != 2 || key.isEmpty() || value.isEmpty()) {
            throw new InvalidFilterException(InvalidFilterException.ErrorType.INVALID_FILTER_FORMAT);
        }

        return createPredicate(key, value);
    }

    /**
     * Creates appropriate predicate based on filter key and value.
     * Usage of predicates referenced from: <a href="https://www.baeldung.com/java-predicate-chain">...</a>
     *
     * @param key filter key (task, done, date)
     * @param value filter value
     * @return predicate that matches the specified criteria
     * @throws InvalidFilterException for unknown keys or invalid values
     */
    public static Predicate<Task> createPredicate(String key, String value) throws InvalidFilterException, InvalidDateTimeException {
        switch (key) {
        case "task":
            return task -> task.getTaskType().getKeyword().equalsIgnoreCase(value);

        case "done":
            boolean isDone = Boolean.parseBoolean(value);
            return task -> task.isDone() == isDone;

        case "date":
            ParsedDateTime parsed = DateTimeParserUtil.parse(value);
            LocalDate filterDate = parsed.dateTime().toLocalDate(); // ignore time

            return task -> {
                List<LocalDateTime> dates = task.getDates();
                if (dates.isEmpty()) return false;

                for (LocalDateTime dt : dates) {
                    if (dt.toLocalDate().equals(filterDate)) {
                        return true;
                    }
                }
                return false;
            };

        default:
            throw new InvalidFilterException(InvalidFilterException.ErrorType.UNKNOWN_FILTER_KEY);
        }
    }
}
