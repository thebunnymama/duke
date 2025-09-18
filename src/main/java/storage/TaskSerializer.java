package storage;

import task.DeadlineTask;
import task.EventTask;
import task.Task;

import java.util.List;

public class TaskSerializer {

    private TaskSerializer() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Utility class to serialize Task objects into JSON string representations.
     * <p>The JSON format includes common task fields (type, done status, description)
     * as well as task-specific fields for different task types.</p>
     *
     * @see Storage#saveTasks()
     */
    public static String tasksToJson(List<Task> taskList) {
        if (taskList.isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < taskList.size(); i++) {
            String taskJson = taskToJson(taskList.get(i));
            String indentedTask = taskJson.replaceAll("(?m)^", "  ");
            sb.append(indentedTask);

            // add comma between tasks
            if (i != taskList.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Converts a single task into JSON string.
     */
    public static String taskToJson(Task t) {
        // Common fields
        String json = """
                {
                  "type":"%s",
                  "done":%s,
                  "description":"%s"
                """.formatted(
                t.getTaskType().getKeyword(),
                t.isDone(),
                t.getDescription().replace("\"", "\\\"")   //"description": "Read \"Java\" book"
        ).stripTrailing();

        // Append task-specific fields
        String specific = "";
        if (t instanceof DeadlineTask dt) {
            specific = String.format(",\n  \"deadline\":\"%s\"", dt.getDates());
        } else if (t instanceof EventTask et) {
            specific = String.format(",\n  \"start\":\"%s\",\n  \"end\":\"%s\"",
                    et.getDates().get(0), et.getDates().get(1));
        }
        return json + specific + "\n}";
    }
}
