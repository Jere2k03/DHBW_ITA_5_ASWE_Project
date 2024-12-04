package control;

/**
 * The {@code OwnExceptions} class provides custom exception classes for the control package.
 * The exceptions are:
 *  - {@link PacketOutOfBoundsException} ...
 * 
 */
public class Exceptions {
    /**
     * This exception is thrown when the values of the package are out of bounds.
     */
    public static class PacketOutOfBoundsException extends Exception {
        /**
         * Constructor for PacketOutOfBoundsException
         * @param message
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
