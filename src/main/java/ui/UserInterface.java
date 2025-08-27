/**
 * Purpose: Handles I/O interactions with users
 */

package ui;

import manager.TaskManager;
import task.Task;
import message.Message;
import message.TaskListMessage;
import message.GoodbyeMessage;
import message.WelcomeMessage;
import message.AddTaskMessage;

import java.util.*;

public class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private final MessageRenderer renderer;
    private static final int MAX_WIDTH = 50;      // For MeeBot text wrap
    private static final int CONSOLE_WIDTH = 90;

    // Constructor for Renderer
    public UserInterface() {
        this.renderer = new MessageRenderer(MAX_WIDTH, CONSOLE_WIDTH);
    }

    // METHODS start here
    // In main, readUserInput() pass the raw string typed by the user
    // Here, that string is wrapped in a Message object and
    // Passed to Renderer via displayMessage to extract the content of message

    // Read in user input
    public String readUserInput() {
        System.out.print("You: ");
        return sc.nextLine().trim();
    }

    // Calls Renderer to display rendered message
    private void displayMessage(Message msg) {
        renderer.render(msg);
    }

    public void displayWelcome() {
        String noodleArt = """
                                    ___  ___ _____  _____ ______  _____  _____\s
                                    |  \\/  ||  ___||  ___|| ___ \\|  _  ||_   _|
                                    | .  . || |__  | |__  | |_/ /| | | |  | | \s
                                    | |\\/| ||  __| |  __| | ___ \\| | | |  | | \s
                                    | |  | || |___ | |___ | |_/ /\\ \\_/ /  | | \s
                                    \\_|  |_/\\____/ \\____/ \\____/  \\___/   \\_/ \s
                """;
        System.out.print(noodleArt);
        displayMessage(new WelcomeMessage());
    }

    public void displayBye() {
        displayMessage(new GoodbyeMessage());
    }

    public void displayAddTask(Task task) {
        AddTaskMessage taskAdded = new AddTaskMessage(task);
        displayMessage(taskAdded);
    }

    public void displayTaskList(TaskManager tm) {
        displayMessage(new TaskListMessage(tm));
    }
}
