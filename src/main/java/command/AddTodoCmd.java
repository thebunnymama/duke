package command;

import manager.TaskManager;
import message.AddTaskMessage;
import message.ErrorMessage;
import message.Message;
import task.Task;
import task.TodoTask;

/**
 * Command to add a simple todo task without any date constraints.
 */
public class AddTodoCmd extends AddTaskCommand {
    private final String args;

    public AddTodoCmd(TaskManager taskManager, String args) {
        super(taskManager);
        this.args = args;
    }

    @Override
    public Message execute() {
        if (args.isBlank()) {
            return new ErrorMessage("Error: Task description cannot be empty.");
        }

        String description = args.trim();

        Task todo = new TodoTask(description);
        taskManager.addTask(todo);
        return new AddTaskMessage(todo, taskManager);
    }
}
