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
        // Level-0: Greet
        ui.displayWelcome();

        while (true) {
            String userInput = ui.readUserInput();

            // Level-1: exit program when user says bye
            if (userInput.equalsIgnoreCase("bye")) {
                ui.displayBye();
                break;

            // Level-2: retrieves all task
            } else if (userInput.equalsIgnoreCase("list")) {
                ui.displayTaskList(tm);

            } else {
                // Level-2: Treat everything as a new task
                Task task = new Task(userInput);
                tm.addTask(task);
                ui.displayAddTask(task);
            }
        }
    }

    public static void main(String[] args) {
        MeeBot mb = new MeeBot();
        mb.run();
    }
}
