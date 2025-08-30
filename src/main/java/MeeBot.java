import command.Command;
import manager.TaskManager;
import message.ErrorMessage;
import message.Message;
import ui.UserInterface;
import parser.CommandProcessor;

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
        CommandProcessor processor = new CommandProcessor(tm);

        while (true) {
            String input = ui.readUserInput();
            Command cmd = processor.parseCommand(input);

            try {
                Message msg = cmd.execute();
                ui.displayMessage(msg);
                if (cmd.isExit()) {
                    break;
                }

            } catch (RuntimeException e) {
                ui.displayMessage(new ErrorMessage("Oops, something went wrong! Please try again."));
            }
        }
    }

    public static void main(String[] args) {
        MeeBot mb = new MeeBot();
        mb.run();
    }
}
