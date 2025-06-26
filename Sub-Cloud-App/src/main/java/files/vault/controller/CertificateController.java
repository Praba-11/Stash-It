package files.vault.controller;

import files.vault.component.azure.storage.blob.FileStorageClient;
import files.vault.domain.dto.MemberRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/certificates")
@Slf4j
public class CertificateController {

    @Value("${spring.cloud.azure.storage.container-name}")
    private String containerName;

    private final FileStorageClient fileStorageClient;

    public CertificateController(FileStorageClient fileStorageClient) {
        this.fileStorageClient = fileStorageClient;
    }

    @PostMapping("/upload")
    public void upload(@RequestParam MultipartFile file, @RequestBody MemberRequestDto dto) throws IOException {

        String blobName =
                String.format(
                        "%s/%s/%s",
                        dto.getDepartment(),
                        dto.getDesignation(),
                        file.getOriginalFilename());

        fileStorageClient.createContainer(containerName);

        try (InputStream inputStream = file.getInputStream()) {
            fileStorageClient.uploadImage(
                    containerName,
                    blobName,
                    inputStream,
                    file.getSize()
            );
        }
    }

}
