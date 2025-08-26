/**
 * Purpose: Handles I/O interactions with users
 */

import message.UserMessage;
import message.GoodbyeMessage;
import message.Message;
import message.WelcomeMessage;
import output.Renderer;
import java.util.*;

public class UI {
    private final Scanner sc = new Scanner(System.in);
    private final Renderer renderer;
    private static final int MAX_WIDTH = 50;      // For MeeBot text wrap
    private static final int CONSOLE_WIDTH = 90;

    // Constructor for Renderer
    public UI() {
        this.renderer = new Renderer(MAX_WIDTH, CONSOLE_WIDTH);
    }

    // METHODS start here
    // Read user input
    public String readUserInput() {
        System.out.print("You: ");
        return sc.nextLine().trim();
    }

    // Calls Renderer to display rendered output
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

    public void displayUserMessage(String input) {
        // In main, readUserInput() pass the raw string typed by the user
        // Here, that string is wrapped in a UserMessage object and
        // Passed to Renderer via displayMessage

        UserMessage userMsg = new UserMessage(input);
        displayMessage(userMsg);
    }

    public void displayBye() {
        displayMessage(new GoodbyeMessage());
    }
}
