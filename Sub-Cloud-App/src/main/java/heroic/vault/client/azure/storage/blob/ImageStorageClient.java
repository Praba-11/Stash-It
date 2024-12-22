package heroic.vault.client.azure.storage.blob;

import java.io.InputStream;

public interface ImageStorageClient {
    String uploadImage(String containerName, String blobName, InputStream data, Long size);
}
