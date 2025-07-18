package files.stash.component.service;

import files.stash.component.azure.storage.blob.FileStorageClient;
import files.stash.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import service.ServiceLayerException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Component responsible for handling file uploads to Azure Blob Storage.
 *
 * <p>This class constructs the blob path based on the member's department and designation
 * and delegates the actual upload to the {@link FileStorageClient}.
 */
@Slf4j
@Component
public class FileStorageCreator {

    private final FileStorageClient fileStorageClient;

    @Value("${spring.cloud.azure.storage.container-name}")
    private String containerName;

    /**
     * Constructs a new {@code FileStorageCreator} with the given storage client.
     *
     * @param fileStorageClient the Azure blob storage client used for file operations
     */
    public FileStorageCreator(FileStorageClient fileStorageClient) {
        this.fileStorageClient = fileStorageClient;
    }

    /**
     * Uploads a file to Azure Blob Storage using a structured blob name.
     *
     * <p>The blob name typically follows the format:
     * <pre>{@code {department}/{designation}/{originalFilename}}</pre>
     * based on member-specific metadata.
     *
     * @param blobName the full blob path where the file will be stored
     * @param file     the file to upload
     * @throws ServiceLayerException if an I/O or unexpected error occurs during upload
     */
    public void create(String blobName, MultipartFile file) throws ServiceLayerException {

        try {
            fileStorageClient.createContainer(containerName);

            try (InputStream inputStream = file.getInputStream()) {
                fileStorageClient.upload(
                        containerName,
                        blobName,
                        inputStream,
                        file.getSize()
                );
            }

        } catch (IOException e) {
            log.error("I/O error while uploading file: {}", e.getMessage(), e);
            throw new ServiceLayerException(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error during file upload: {}", e.getMessage(), e);
            throw new ServiceLayerException(e.getMessage());
        }
    }
}
