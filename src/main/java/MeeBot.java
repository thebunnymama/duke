/**
 * Instantiate main objects (only UI for now)
 * Start chatbot loop
 */

public class MeeBot {

    private final UI ui;

    // Constructor
    public MeeBot() {
        this.ui = new UI();
    }

    public void run() {
        // Level-0: Greet
        ui.displayWelcome();

        while (true) {
            String userInput = ui.readUserInput();

            // Level-1: Exit program when user says bye
            if (userInput.equalsIgnoreCase("bye")) {
                ui.displayBye();
                break;
            }

            // Level-1: echo back user's input
            ui.displayUserMessage(userInput);
        }
    }

    public static void main(String[] args) {
        MeeBot mb = new MeeBot();
        mb.run();
    }
}
