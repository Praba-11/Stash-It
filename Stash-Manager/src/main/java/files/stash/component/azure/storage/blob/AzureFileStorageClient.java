package files.stash.component.azure.storage.blob;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobStorageException;
import commons.exceptions.cloud.azure.AzureBlobStorageException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Azure Blob Storage implementation of {@link FileStorageClient}.
 *
 * <p>This class provides functionality to upload files and create containers
 * in Microsoft Azure Blob Storage using the Azure SDK for Java.
 */
@Service
public class AzureFileStorageClient implements FileStorageClient {

    private final BlobServiceClient blobServiceClient;

    /**
     * Constructs an {@code AzureFileStorageClient} with the specified {@link BlobServiceClient}.
     *
     * @param blobServiceClient the Azure Blob service client used to access containers and blobs
     */
    public AzureFileStorageClient(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    /**
     * Uploads a file to the specified Azure Blob Storage container.
     *
     * <p>The method refactors the original blob name by appending a random UUID to prevent filename conflicts.
     *
     * @param containerName the name of the container where the blob will be uploaded
     * @param blobName      the original name or path of the blob (used for extension extraction)
     * @param data          the input stream of the file content
     * @param size          the size of the file in bytes
     * @return the URL of the uploaded blob
     * @throws AzureBlobStorageException if any error occurs during upload
     */
    @Override
    public String upload(String containerName, String blobName, InputStream data, Long size) {
        try {
            // Generate a unique blob name to avoid collisions
            String refactoredImageName = UUID.randomUUID() + blobName.substring(blobName.lastIndexOf("."));

            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            containerClient.getBlobClient(refactoredImageName).upload(data, size);

            return containerClient.getBlobClient(refactoredImageName).getBlobUrl();
        } catch (BlobStorageException exception) {
            throw new AzureBlobStorageException(exception.getMessage(), exception);
        }
    }

    /**
     * Creates a new Azure Blob Storage container if it does not already exist.
     *
     * @param containerName the name of the container to create
     * @throws AzureBlobStorageException if any error occurs during container creation
     */
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

    /**
     * Downloads a blob from Azure Blob Storage and returns it as a File.
     *
     * @param containerName the name of the blob container
     * @param blobPath the path or folder structure within the container (e.g., "dept/designation")
     * @param fileName the name of the blob (e.g., "resume.pdf")
     * @return a File object pointing to the downloaded blob
     * @throws AzureBlobStorageException if any error occurs during blob retrieval
     */
    public File downloadBlobAsFile(String containerName, String blobPath, String fileName) {
        try {
            // Construct full blob name from path and filename
            String fullBlobName = blobPath.endsWith("/") ? blobPath + fileName : blobPath + "/" + fileName;

            BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
            BlobClient blobClient = containerClient.getBlobClient(fullBlobName);

            if (!blobClient.exists()) {
                throw new AzureBlobStorageException("Blob not found: " + fullBlobName);
            }

            // Create a temporary file to store the downloaded blob
            File tempFile = File.createTempFile("downloaded-", "-" + fileName);
            blobClient.downloadToFile(tempFile.getAbsolutePath(), true); // true = overwrite if exists

            return tempFile;

        } catch (IOException | BlobStorageException e) {
            throw new AzureBlobStorageException("Failed to download blob: " + fileName, e);
        }
    }

}
