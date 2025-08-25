/**
 * Purpose: Handles all I/O interactions with the user
 */

import java.util.Scanner;

public class UI {
    private final Scanner sc;

    // Constructor
    public UI() {
        sc = new Scanner(System.in);
    }

    // METHODS start here
    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void displayWelcome() {
        String noodleArt = """
                    ⠀⠉⠛⠛⠛⠶⠶⠦⠤⢤⣄⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⣠⣤⣤⣤⣄⣀⣀⣀⣀⡀⠀⠈⠉⠉⠙⠛⠛⠶⠶⠶⣤⡄⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠙⠛⠛⠛⠛⠳⠶⠶⠶⠶⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡟⢀⣴⠂⣠⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡟⢀⡾⠃⣴⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣄⠘⣧⡀⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣦⠈⢷⡄⠹⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠟⠀⠼⠃⠰⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    ⠀⠀⢰⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⡆⠀⠀
                    ⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀
                    ⠀⠀⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠙⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠋⠀⠀⠀⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢉⣉⣉⣉⣉⣉⣉⡉⠁⠀⠀
                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                """;
        System.out.print(noodleArt);
        System.out.println("     MeeBot: Hello, I'm MeeBot.");
        System.out.println("     MeeBot: Your steady mee pok buddy!");
        System.out.println("     MeeBot: What can mee stir up for you today?");
    }

    public void displayBye() {
        System.out.println("     MeeBot: Ok lah, mee go already. Catch you later!");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
