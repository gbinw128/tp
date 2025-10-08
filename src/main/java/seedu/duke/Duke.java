package seedu.duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static ArrayList<Finance> expenseArrayList = new ArrayList<Finance>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine().trim();
        while (!userInput.equals("exit")) {
            handleInput(userInput);
            userInput = in.nextLine().trim();
        }
        System.out.println("Bye! *inserts motivational music and quote* You are amazing!");
    }

    public static void handleInput(String line) {
        String command = commandCheck(line);
        switch (command) {
            case "list expense":
                listExpense();
                break;
            case "add expense":
                addExpense(line);
                break;
            case "delete expense":
                deleteExpense(line);
                break;
            default:
                invalidCommandMessage();
                break;
        }
    }

    private static void listExpense() {
        System.out.println("\tHere are the expenses in your list:");
        for (Finance expense : expenseArrayList) {
            int printIndex = expenseArrayList.indexOf(expense) + 1;
            System.out.println("\t\t" + printIndex + ". " + expense);
        }
    }

    private static void addExpense(String line) {
        int descriptionDividerPos = line.indexOf("d/");
        int amountDividerPos = line.indexOf("a/");

        String description = line.substring(descriptionDividerPos + 2, amountDividerPos).trim();
        System.out.println(description);
        String amount = line.substring(amountDividerPos + 2).trim();
        System.out.println("amount: " + amount);

        expenseArrayList.add(new Expense(description, amount));
    }

    private static void deleteExpense(String line) {
        int deleteWordSize = "delete expense".length();
        if (line.length() <= deleteWordSize) {
            //throw exception, no number
        }
        String deleteNumber = line.substring(deleteWordSize).trim();
        int expenseIndexToDelete = Integer.parseInt(deleteNumber) - 1;
        if (expenseIndexToDelete >= expenseArrayList.size() || expenseIndexToDelete < 0) {
            //throw exception, number out of bounds
        }
        System.out.println("removed!");
        expenseArrayList.remove(expenseIndexToDelete);
    }


    public static String commandCheck(String line) {
        String appendedLine = line.trim().toLowerCase();
        if (appendedLine.startsWith("list expense")) {
            return "list expense";
        } else if (appendedLine.startsWith("add expense")) {
            return "add expense";
        } else if (appendedLine.startsWith("delete expense")) {
            return "delete expense";
        }
        return "";
    }

    private static void invalidCommandMessage() {
        System.out.println("Invalid command");
    }
}
