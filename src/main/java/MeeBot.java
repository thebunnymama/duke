public class MeeBot {

    private final UI ui;

    // Constructor
    public MeeBot() {
        this.ui = new UI();
    }

    public void run() {
        ui.displayWelcome();
        ui.displayMessage("     MeeBot: Mee is at Lvl 0 so I only know how to say hi and bye for now");
        ui.displayBye();
    }

    public static void main(String[] args) {
        MeeBot mb = new MeeBot();
        mb.run();
    }
}
