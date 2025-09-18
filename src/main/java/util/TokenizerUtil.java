package util;

import exception.FileContentException;
import exception.InvalidTaskFormatException;
import exception.InvalidTaskFormatException.ErrorType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class providing methods for tokenizing strings and JSON arrays.
 * <p>This class contains static utility methods for parsing command-line arguments using
 * regex patterns and for splitting JSON arrays into individual JSON object strings.</p>
 */
public final class TokenizerUtil {

    private TokenizerUtil() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Tokenizes a string into an array of tokens using the specified regex pattern.
     * <p>
     * Validates that the input string matches the expected pattern and contains
     * the required number of capture groups. Each captured token is trimmed of
     * whitespace and validated to be non-empty.
     *
     * @param args       the input string to tokenize (must not be null or blank)
     * @param pattern    the regex pattern with capture groups for tokenization
     * @param tokenCount the expected number of tokens to extract
     * @param errorType  the error type to throw if tokenization fails
     * @return an array of trimmed, non-empty tokens
     * @throws InvalidTaskFormatException if the input is null/blank, doesn't match
     *                                    the pattern, has wrong number of groups, or contains empty tokens
     */
    public static String[] tokenize(String args, Pattern pattern, int tokenCount, ErrorType errorType)
            throws InvalidTaskFormatException {

        if (args == null || args.isBlank()) {
            throw ErrorType.MISSING_DESCRIPTION.createException();
        }

        Matcher matcher = pattern.matcher(args.trim());
        if (!matcher.matches() || matcher.groupCount() != tokenCount) {
            throw errorType.createException();
        }

        String[] tokens = new String[tokenCount];
        for (int i = 0; i < tokenCount; i++) {
            tokens[i] = matcher.group(i + 1).trim();
            if (tokens[i].isEmpty()) {
                throw errorType.createException();
            }
        }
        return tokens;
    }

    /**
     * Splits a JSON array string into individual JSON object strings.
     * <p>
     * Parses a JSON array and extracts each JSON object as a separate string.
     * Handles both single objects and arrays containing multiple objects.
     * <p>
     * <strong>Usage Note:</strong> This is a simplified JSON parser that uses regex
     * splitting and may not handle all edge cases of complex JSON structures.
     * It assumes well-formed JSON objects separated by commas.
     *
     * @param jsonArray the JSON array string to tokenize (e.g., "[{...}, {...}]")
     * @return a list of individual JSON object strings, each representing one object
     * from the array (without surrounding array brackets)
     * @throws IllegalArgumentException if the input is not a valid JSON array format
     */
    public static List<String> tokenize(String jsonArray) {
        List<String> tokens = new ArrayList<>();
        String json = jsonArray.trim();

        if (json.equals("[]") || json.isBlank()) return tokens;

        if (!json.startsWith("[") || !json.endsWith("]")) {
            throw new FileContentException(FileContentException.ErrorType.INVALID_JSON_FORMAT);
        }

        json = json.substring(1, json.length() - 1).trim(); // Remove surrounding square brackets

        // Handle multiple objects
        if (json.matches(".*}\\s*,\\s*\\{.*")) {
            String[] joArray = json.split("(?<=})\\s*,\\s*(?=\\{)");
            for (String jo : joArray) {
                tokens.add(jo.trim());
            }
        } else {
            tokens.add("{" + json + "}");   // single object
        }

        return tokens;
    }
}
