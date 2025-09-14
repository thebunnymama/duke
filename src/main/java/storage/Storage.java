package storage;

import manager.TaskManager;
import task.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles persistent storage of tasks to and from the file system.
 * <p>
 * This class manages the creation and maintenance of storage directories and files,
 * and provides methods to save tasks in JSON format. It uses a key-based access pattern
 * to ensure that only authorized operations can modify the underlying task data.
 */
public class Storage {

    /**
     * Authorization token for accessing modifiable task list.
     * <p>
     * This nested class implements a capability-based access pattern where only the
     * Storage class can create valid token instances. This ensures controlled access
     * to sensitive operations like direct task list modification.
     * <p>
     * The private constructor prevents external instantiation, making this an
     * effective access control mechanism.
     */
    public static final class Key {
        private Key() {}
    }

    /** The unique access token for Storage instance */
    private final Key key = new Key();
    private static final String DEFAULT_DIR = "data";
    private static final String DEFAULT_FILE = "tasks.json";
    private final File dataFile;
    private final TaskManager tm;


    public Storage(TaskManager tm) {
        this.tm = tm;
        this.dataFile = initStorage();
    }

    /**
     * Initializes the storage directory if it doesn't exist, and creates an empty
     * storage file initialized with "[]" if the file doesn't exist.
     *
     * @return the File object representing the storage file
     * @throws RuntimeException if the directory cannot be created or if there's
     *         an IOException while creating or writing to the storage file
     */
    private File initStorage() {
        File dir = new File(DEFAULT_DIR);

        if (!dir.exists() && !dir.mkdirs()) {
            throw new RuntimeException("Unable to create storage directory: " + dir.getAbsolutePath());
        }

        File file = new File(dir, DEFAULT_FILE);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("[]");
            } catch (IOException e) {
                throw new RuntimeException("Unable to create storage file: " + file.getAbsolutePath());
            }
        }
        return file;
    }

    /**
     * Saves all current tasks to the storage file in JSON format.
     * <p>
     * Retrieves the current task list from the TaskManager using the authorized
     * access token, serializes it to JSON format using TaskSerializer, and writes it to
     * the storage file. The entire file is overwritten with the current state.
     *
     * @throws RuntimeException if there's an IOException while writing to the file
     * @see TaskManager#getModifiableList(Key)
     * @see TaskSerializer#tasksToJson(List)
     */
    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            List<Task> taskList = tm.getModifiableList(key);
            writer.write(TaskSerializer.tasksToJson(taskList));
        } catch (IOException e) {
            throw new RuntimeException("Failed to save tasks", e);
        }
    }
}
