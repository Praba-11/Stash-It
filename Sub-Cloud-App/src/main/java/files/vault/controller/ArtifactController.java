package files.vault.controller;

import files.vault.client.azure.storage.blob.ImageStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/artifact")
@Slf4j
public class ArtifactController {

    private final ImageStorageClient imageStorageClient;

    public ArtifactController(ImageStorageClient imageStorageClient) {
        this.imageStorageClient = imageStorageClient;
    }

    @PostMapping("/images")
    public void uploadImage(@RequestParam String containerName, @RequestParam MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            imageStorageClient.uploadImage(containerName, file.getOriginalFilename(), inputStream, file.getSize());
        }
    }

}
