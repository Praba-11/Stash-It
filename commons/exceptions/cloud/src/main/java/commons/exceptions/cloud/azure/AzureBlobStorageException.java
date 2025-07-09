/**
 * Copyright (C) Appranix, Inc - All Rights Reserved.
 *
 * <p>Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * <p>Proprietary and confidential.
 */
package commons.exceptions.cloud.azure;

/**
 * Exception thrown to indicate errors related to Azure Blob Storage operations.
 * <p>
 * This unchecked exception wraps failures encountered while interacting with
 * Azure Blob Storage services.
 */
public class AzureBlobStorageException extends RuntimeException {

    private static final long serialVersionUID = 3876153956597522512L;

    /**
     * Constructs a new {@code AzureBlobStorageException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     */
    public AzureBlobStorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code AzureBlobStorageException} with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method)
     * @param cause   the cause (which is saved for later retrieval by the {@link #getCause()} method)
     */
    public AzureBlobStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
