package service;

/**
 * Custom unchecked exception representing errors occurring in the service layer.
 * <p>
 * This exception is typically thrown when business logic or service operations fail.
 */
public class ServiceLayerException extends RuntimeException {

    /**
     * Constructs a new {@code ServiceLayerException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception.
     */
    public ServiceLayerException(String message) {
        super(message);
    }
}
