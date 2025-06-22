package files.vault.component.azure.storage.blob;

import commons.exceptions.cloud.azure.AzureBlobStorageException;

import java.io.InputStream;

public interface FileStorageClient {
    String uploadImage(String containerName, String blobName, InputStream data, Long size) throws AzureBlobStorageException;

    void createContainer(String containerName) throws AzureBlobStorageException;
}
