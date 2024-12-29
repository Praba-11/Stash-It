/**
 * Copyright (C) Appranix, Inc - All Rights Reserved.
 *
 * <p>Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * <p>Proprietary and confidential.
 */
package commons.exceptions.cloud.azure;

public class AzureBlobStorageException extends RuntimeException {

    private static final long serialVersionUID = 3876153956597522512L;

    /**
     * Constructs a new BlobStorageException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public AzureBlobStorageException(String message) {
        super(message);
    }

    /**
     * Constructs a new BlobStorageException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public AzureBlobStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
