package model;

public class InvalidUserInputException extends Exception {

    public InvalidUserInputException() {
        super("Invalid input! No operations can be performed on this set of data. " +
                "Please try again.");
    }
}
