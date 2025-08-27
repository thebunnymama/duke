package task;

public class Task {
    // Represents a unit of work
    // Fields: description
    // Methods: getDescription()

    private String description;

    public Task (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
