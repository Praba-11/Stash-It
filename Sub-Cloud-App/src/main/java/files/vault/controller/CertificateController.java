package files.vault.controller;

import files.vault.component.azure.storage.blob.FileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/certificates")
@Slf4j
public class CertificateController {

    private final FileStorageClient fileStorageClient;

    public CertificateController(FileStorageClient fileStorageClient) {
        this.fileStorageClient = fileStorageClient;
    }

    @PostMapping("/upload")
    public void uploadCertificate(@RequestParam String containerName, @RequestParam MultipartFile file) throws IOException {

        fileStorageClient.createContainer(containerName);

        try (InputStream inputStream = file.getInputStream()) {
            fileStorageClient.uploadImage(
                    containerName,
                    file.getOriginalFilename(),
                    inputStream,
                    file.getSize()
            );
        }
    }

}
