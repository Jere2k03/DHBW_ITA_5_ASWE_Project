package control;

/**
 * The {@code OwnExceptions} class provides custom exception classes for the control package.
 * The exception is:
 *  - {@link PacketOutOfBoundsException} which is thrown when the values of the package are out of bounds.
 * 
 */
public class Exceptions {
    /**
     * This exception is thrown when the values of the package are out of bounds.
     */
    public static class PacketOutOfBoundsException extends Exception {
        /**
         * Constructor for PacketOutOfBoundsException
         * @param message The message to display when the exception is thrown
         */
        public PacketOutOfBoundsException(String message) {
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
