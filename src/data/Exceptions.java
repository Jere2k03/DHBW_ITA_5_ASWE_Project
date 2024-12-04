package data;

/**
 * The {@code Exceptions} class provides custom exception classes for the data package.
 * The exceptions are:
 *  - {@link CSVWrongFormatException} ...
 *  - {@link CSVNotFoundException} ...
 * 
 */
public class Exceptions {
    /**
     * This exception is thrown when the CSV file is not found.
     */
    public static class CSVWrongFormatException extends Exception {
        /**
         * Constructor for CSVWrongFormatException
         * @param message
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
