package files.vault.client.azure.storage.blob;

import commons.exceptions.cloud.azure.AzureBlobStorageException;

import java.io.InputStream;

public interface ImageStorageClient {
    String uploadImage(String containerName, String blobName, InputStream data, Long size) throws AzureBlobStorageException;
}
