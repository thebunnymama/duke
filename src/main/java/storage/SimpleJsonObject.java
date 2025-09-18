package storage;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimal JSON object representation for flat key-value pairs.
 * <p>
 * This class provides a simple interface for storing and retrieving
 * string-based key-value data. It is designed for use cases where
 * only flat, string-based data structures are needed.
 *
 * @see TaskDeserializer
 */
public class SimpleJsonObject {
    private final Map<String, String> fields = new HashMap<>();

    /**
     * Stores a key-value pair in this JSON object.
     */
    public void put(String key, String value) {
        fields.put(key, value);
    }

    /**
     * Retrieves the value associated with the specified key.
     */
    public String get(String key) {
        return fields.get(key);
    }
}
