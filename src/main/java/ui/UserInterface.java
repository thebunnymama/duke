/**
 * Purpose: Handles I/O interactions with users
 */

package ui;

import message.*;
import task.Task;
import manager.TaskManager;

import java.util.*;

public class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private final MessageRenderer renderer;
    private static final int MAX_WIDTH = 50;      // For MeeBot text wrap
    private static final int CONSOLE_WIDTH = 90;

    public UserInterface() {
        this.renderer = new MessageRenderer(MAX_WIDTH, CONSOLE_WIDTH);
    }

    public String readUserInput() {
        System.out.print("You: ");
        return sc.nextLine().trim();
    }

    // Calls Renderer to display rendered message
    public void displayMessage(Message msg) {
        renderer.render(msg);
    }

    public void displayWelcome() {
        // Display ASCII logo once as part of welcome message
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

}
