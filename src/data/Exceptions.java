package data;

/**
 * The {@code Exceptions} class provides custom exception classes for the data package.
 * The exception is:
 *  - {@link CSVWrongFormatException} which is thrown when the CSV file is not found, or has the wrong format.
 * 
 */
public class Exceptions {
    /**
     * This exception is thrown when the CSV file is not found.
     */
    public static class CSVWrongFormatException extends Exception {
        /**
         * Constructor for CSVWrongFormatException
         * @param message The message to display when the exception is thrown
         */
        public CSVWrongFormatException(String message) {
            super(message);
        }
    }

    /**
     * Constructor for Exceptions class
     */
    private Exceptions() {
        // empty constructor to prevent instantiation
    }
}
