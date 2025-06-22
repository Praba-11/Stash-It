package files.vault.component.azure.storage.blob;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobStorageException;
import commons.exceptions.cloud.azure.AzureBlobStorageException;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureFileStorageClient implements FileStorageClient {

    private final BlobServiceClient blobServiceClient;

    public AzureFileStorageClient(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    @Override
    public String uploadImage(String containerName, String blobName, InputStream data, Long size) {

        try {
            // Refactor the image name to avoid conflicts
            String refactoredImageName = UUID.randomUUID() + blobName.substring(blobName.lastIndexOf("."));

            // Create a BlobContainerClient object which will be used to create a container
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

            // Create the container if it does not exist
            containerClient.getBlobClient(refactoredImageName).upload(data, size);

            // Return the URL of the uploaded image
            return containerClient.getBlobClient(blobName).getBlobUrl();
        } catch (BlobStorageException exception) {
            throw new AzureBlobStorageException(exception.getMessage(), exception);
        }
    }

    @Override
    public void createContainer(String containerName) {

        try {
            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

            if (!containerClient.exists()) {
                containerClient.create();
            }
        } catch (BlobStorageException exception) {
            throw new AzureBlobStorageException(exception.getMessage(), exception);
        }
    }
}
