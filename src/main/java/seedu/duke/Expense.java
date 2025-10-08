package seedu.duke;

public class Expense extends Finance {
    public Expense(String description, String amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense: " + description + ", $" + amount;
    }
}
