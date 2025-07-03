package files.vault.controller;

import commons.exceptions.service.ServiceLayerException;
import files.vault.component.azure.storage.blob.FileStorageClient;
import files.vault.component.service.ArtifactBuilder;
import files.vault.component.service.FileStorageCreator;
import files.vault.domain.dto.ArtifactUploadRequestDto;
import files.vault.domain.dto.MemberAndArtifactUploadRequestDto;
import files.vault.domain.entity.Artifact;
import files.vault.domain.entity.ArtifactType;
import files.vault.service.ArtifactService;
import files.vault.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/artifact")
@Slf4j
public class ArtifactController {

    @Value("${spring.cloud.azure.storage.container-name}")
    private String containerName;

    private final FileStorageCreator fileStorageCreator;

    private final ArtifactBuilder artifactBuilder;

    private final ArtifactService artifactService;

    private final MemberService memberService;

    public ArtifactController(FileStorageCreator fileStorageCreator, ArtifactBuilder artifactBuilder, ArtifactService artifactService, MemberService memberService) {
        this.fileStorageCreator = fileStorageCreator;
        this.artifactBuilder = artifactBuilder;
        this.artifactService = artifactService;
        this.memberService = memberService;
    }

    /**
     * Uploads a file artifact associated with a new member.
     *
     * <p>This endpoint accepts both a multipart file and member details, then stores
     * the file in Azure Blob Storage (or another configured backend) under a
     * structured path based on the member's department and designation.
     *
     * @param file the multipart file to be uploaded
     * @param dto  the data transfer object containing member and artifact details
     * @return {@link ResponseEntity} with appropriate HTTP status:
     *         <ul>
     *           <li>{@code 201 CREATED} if the upload is successful</li>
     *           <li>{@code 500 INTERNAL_SERVER_ERROR} if an I/O error occurs while reading the file</li>
     *           <li>{@code 400 BAD_REQUEST} for any other unexpected error</li>
     *         </ul>
     */
    @PostMapping("/new/member/upload")
    public ResponseEntity<HttpStatus> upload(@RequestParam MultipartFile file, @RequestBody ArtifactUploadRequestDto dto) {

        try {
            log.info("Artifact create initiated. Artifact name: {}", dto.getArtifactTitle());

            fileStorageCreator.create(member, file);

            Artifact artifact = artifactBuilder.build(dto);
            artifactService.create(artifact);

            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (ServiceLayerException e) {
            log.error("Service layer exception occurred: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            log.error("Unexpected error during member and artifact creation: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Retrieves all available artifact types defined in the system.
     *
     * <p>This endpoint returns a list of all {@link ArtifactType} values,
     * including both the enum constant name and its human-readable display name.
     * This can be used to populate dropdowns or filter options in the UI.
     *
     * @return HTTP 200 OK with a list of artifact type name-displayName pairs
     * @throws IOException if an input/output error occurs (reserved for future extensions)
     *
     * <p><b>Sample Response:</b>
     * <pre>
     * [
     *   { "name": "DOCUMENT", "displayName": "Document" },
     *   { "name": "CERTIFICATE", "displayName": "Certificate" },
     *   ...
     * ]
     * </pre>
     */
    @GetMapping("/find/artifact/types")
    public ResponseEntity<List<Map<String, String>>> findAllArtifactType() throws IOException {
        List<Map<String, String>> types =
                Stream.of(ArtifactType.values())
                        .map(type -> {
                            Map<String, String> typeInfo = new HashMap<>();
                            typeInfo.put("name", type.name());
                            typeInfo.put("displayName", type.getDisplayName());
                            return typeInfo;
                        })
                        .collect(Collectors.toList());

        return ResponseEntity.ok(types);
    }

}
