/**
 * Copyright (C) Appranix, Inc - All Rights Reserved.
 *
 * <p>Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * <p>Proprietary and confidential.
 */
package heroic.vault.client.azure.storage.blob;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureImageStorageClient implements ImageStorageClient {

    private final BlobServiceClient blobServiceClient;

    public AzureImageStorageClient(BlobServiceClient blobServiceClient) {
        this.blobServiceClient = blobServiceClient;
    }

    @Override
    public String uploadImage(String containerName, String blobName, InputStream data, Long size) {

        // Refactor the image name to avoid conflicts
        String refactoredImageName = UUID.randomUUID() + blobName.substring(blobName.lastIndexOf("."));

        // Create a BlobContainerClient object which will be used to create a container
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        // Create the container if it does not exist
        containerClient.getBlobClient(refactoredImageName).upload(data, size);

        // Return the URL of the uploaded image
        return containerClient.getBlobClient(blobName).getBlobUrl();
    }
}
