import manager.TaskManager;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;
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
            case "todo":
            case "deadline":
            case "event":
                handleAddTask(input);
                break;
            default:
                System.out.println("Unknown task type: " + command);
        }
    }

    /**
     * Handles creation of correct task (todo, deadline or event).
     */
    private void handleAddTask(String input) {
        String[] parts = input.split("\\s+", 2);

        if (parts.length < 2) {
            System.out.println("Error: Missing task description");
            return;
        }

        String command = parts[0].toLowerCase();
        String details = parts[1].trim();

        switch (command) {
            case "todo":
                Task todo = new TodoTask(details);
                tm.addTask(todo);
                ui.displayAddTask(todo, tm);
                break;

            case "deadline":
                // Expect format: description /by dateTime
                String[] deadlineParts = details.split("\\s*/by\\s*", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("Error: Deadline task must have '/by <dateTime>'");
                    return;
                }
                Task deadline = new DeadlineTask(deadlineParts[0], deadlineParts[1]);
                tm.addTask(deadline);
                ui.displayAddTask(deadline, tm);
                break;

            case "event":
                // Expect format: description /from dateTime
                String[] eventParts = details.split("\\s*/from\\s*", 2);
                if (eventParts.length < 2) {
                    System.out.println("Error: Event task must have '/from <dateTime>'");
                    return;
                }
                Task event = new EventTask(eventParts[0], eventParts[1]);
                tm.addTask(event);
                ui.displayAddTask(event, tm);
                break;

            default:
                System.out.println("Unknown task type: " + command);
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
