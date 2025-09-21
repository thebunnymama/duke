package task;

import org.junit.jupiter.api.Test;
import util.ParsedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * To test for:
 * <ol>
 * <li>Polymorphism: copy() returns the right subclass automatically</li>
 * <li>Independence: clones are not the same reference as originals</li>
 * <li>Value equality: methods returns equal data</li>
 * </ol>
 */
class CloneTaskTest {
    @Test
    void todoTaskClone() {
        Task original = new TodoTask("water plants");
        Task clone = original.copy();

        // Test: description and completion
        assertEquals(original.getDescription(), clone.getDescription());
        assertEquals(original.isDone(), clone.isDone());

        // Test: date-time
        assertEquals(List.of(), original.getDates());
        assertEquals(List.of(), clone.getDates());
        assertNotSame(original, clone); // different object references
    }

    @Test
    void deadlineTaskClone() {
        ParsedDateTime pdt = new ParsedDateTime(LocalDateTime.of(2025, 9, 21, 10, 10), true);
        Task original = new DeadlineTask("pay bills", pdt);
        Task clone = original.copy();

        // Test: description and completion
        assertEquals(original.getDates(), clone.getDates());
        assertEquals(original.isDone(), clone.isDone());

        // Test: date-time
        assertEquals(original.getDates(), clone.getDates());
        assertNotSame(original, clone); // different object references
    }

    @Test
    void eventTaskClone() {
        ParsedDateTime start = new ParsedDateTime(LocalDateTime.of(2025, 9, 22, 10, 0), true);
        ParsedDateTime end   = new ParsedDateTime(LocalDateTime.of(2025, 9, 22, 10, 10), true);
        Task original = new EventTask("Meeting", start, end);
        Task clone = original.copy();

        // Test: description and completion
        assertEquals(original.getDates(), clone.getDates());
        assertEquals(original.isDone(), clone.isDone());

        // Test: date-time
        assertEquals(original.getDates(), clone.getDates());
        assertNotSame(original, clone); // different object references
    }
}
