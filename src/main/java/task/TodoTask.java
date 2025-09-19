package task;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Simple task without date/time constraints.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
       super(description);
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public List<LocalDateTime> getDates() {
        return Collections.emptyList();
    }

    @Override
    public String toJsonFields() {
        return "";
    }
}
