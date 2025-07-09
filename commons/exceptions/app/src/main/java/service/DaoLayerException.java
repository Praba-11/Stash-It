package service;

/**
 * Custom unchecked exception representing errors occurring in the DAO (Data Access Object) layer.
 * <p>
 * This exception is typically thrown when there is a failure related to database operations or
 * data persistence logic.
 */
public class DaoLayerException extends RuntimeException {

    /**
     * Constructs a new {@code DaoLayerException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public DaoLayerException(String message) {
        super(message);
    }
}
