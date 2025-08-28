import manager.TaskManager;
import task.Task;
import ui.UserInterface;

/**
 * Instantiate main objects (UI, TaskManager for now)
 * Start chatbot loop
 */

public class MeeBot {

    private final UserInterface ui;
    private final TaskManager tm;

    // Constructor
    public MeeBot() {
        this.ui = new UserInterface();
        this.tm = new TaskManager();
    }

    public void run() {
        ui.displayWelcome();

        while (true) {
            String userInput = ui.readUserInput();
            if (userInput.equalsIgnoreCase("bye")) {
                ui.displayBye();
                break;
            }
            handleCommand(userInput.trim());
        }
    }

    /**
     * Processes user commands and delegates to appropriate handlers
     */
    private void handleCommand(String input) {
        String[] parts = input.split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
            case "list":
                ui.displayTaskList(tm);
                break;
            case "mark":
            case "unmark":
                handleMarkUnmark(command, parts);
                break;
            default:
                Task t = new Task(input);
                tm.addTask(t);
                ui.displayAddTask(t);
        }
    }

    /**
     * Handles both "mark" and "unmark" commands to update task status.
     */
    private void handleMarkUnmark(String command, String[] parts) {
        if (parts.length < 2) {
            System.out.println("Error, specify task number");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Error, invalid task number format");
            return;
        }

        Task task = tm.getTask(taskNumber);
        if (command.equals("mark")) {
            if (task.isDone()) {
                System.out.println("Task is already done: " + task);
            } else {
                task = tm.markTaskDone(taskNumber);
                ui.displayTaskMarkedDone(task, taskNumber);
            }

        } else { // "unmark"
            if (!task.isDone()) {
                System.out.println("Task is already unmarked: " + task);
            } else {
                task = tm.unmarkTask(taskNumber);
                ui.displayTaskUnmarked(task, taskNumber);
            }
        }
    }


    public static void main(String[] args) {
        MeeBot mb = new MeeBot();
        mb.run();
    }
}
