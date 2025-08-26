/**
 * Takes in appropriate message from UI and
 * Handles formatting and printing for MeeBot's output
 */

package output;

import message.Message;
import java.util.*;


public class Renderer {
    private final int maxWidth;
    private final int consoleWidth;

    public Renderer(int maxWidth, int consoleWidth) {
        this.maxWidth = maxWidth;
        this.consoleWidth = consoleWidth;
    }

    // Right-align MeeBot's output
    public void render(Message msg) {
        String content = msg.getMessage();

        // Print output right-aligned
        int labelPadding = consoleWidth - "MeeBot:".length();
        System.out.println("\n" + " ".repeat(Math.max(labelPadding, 0)) + "MeeBot:");

        String[] sentences = content.split("\n");
        for (String sentence : sentences) {
            for (String line : wrapText(sentence)) {
                int messagePadding = consoleWidth - line.length();
                System.out.println(" ".repeat(Math.max(messagePadding, 0)) + line);
            }
        }
    }

    // Text wrap
    private List<String> wrapText(String msg) {
        List<String> lines = new ArrayList<>();
        String[] words = msg.split(" ");
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (line.length() + word.length() + 1 > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word);
            } else {
                if (!line.isEmpty()) line.append(" ");
                line.append(word);
            }
        }
        if (!line.isEmpty()) lines.add(line.toString());
        return lines;
    }

}
