package files.stash.component.azure.storage.blob;


import commons.exceptions.cloud.azure.AzureBlobStorageException;

import java.io.InputStream;

/**
 * Interface for performing file operations in Azure Blob Storage.
 *
 * <p>This abstraction provides methods to upload files and create containers
 * in the configured Azure Storage account.
 */
public interface FileStorageClient {

    /**
     * Uploads a file to the specified Azure Blob Storage container.
     *
     * @param containerName the name of the container where the blob will be uploaded
     * @param blobName      the name/path of the blob within the container
     * @param data          the input stream containing file content
     * @param size          the size of the file in bytes
     * @return the URL or identifier of the uploaded blob (implementation-defined)
     * @throws AzureBlobStorageException if the upload operation fails
     */
    String upload(String containerName, String blobName, InputStream data, Long size) throws AzureBlobStorageException;

    /**
     * Creates a new container in Azure Blob Storage if it does not already exist.
     *
     * @param containerName the name of the container to create
     * @throws AzureBlobStorageException if the container creation fails
     */
    void createContainer(String containerName) throws AzureBlobStorageException;
}
