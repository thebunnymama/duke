package manager;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    // Collects tasks - data structure: ArrayList
    // Manages CRUD operations
    // Methods: addTask(), getTask()

    private final List<Task> taskList = new ArrayList<>();

    //METHODS start here

    // Adds a task into the end of list
    public void addTask(Task task) {
        taskList.add(task);
    }

    // Returns all task in list format
    public List<Task> getTask() {
        return taskList;
    }
}
