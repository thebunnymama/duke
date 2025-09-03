package parser;

import java.time.LocalDateTime;

public class ParsedDateTime {
    private final LocalDateTime dateTime;
    private final boolean hasTime;

    public ParsedDateTime(LocalDateTime dateTime, boolean hasTime) {
        this.dateTime = dateTime;
        this.hasTime = hasTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public boolean hasTime() {
        return hasTime;
    }
}
