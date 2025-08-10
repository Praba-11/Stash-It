package files.stash.controller;

import files.stash.component.service.ArtifactBuilder;
import files.stash.component.service.FileStorageCreator;
import files.stash.domain.dto.ArtifactUploadRequestDto;
import files.stash.domain.entity.Artifact;
import files.stash.domain.entity.ArtifactType;
import files.stash.domain.entity.Member;
import files.stash.service.ArtifactService;
import files.stash.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ServiceLayerException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/artifact")
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
    @PostMapping("/upload")
    public ResponseEntity<HttpStatus> upload(@RequestParam MultipartFile file, @RequestBody ArtifactUploadRequestDto dto) {

        try {
            log.info("Artifact create initiated. Artifact name: {}", dto.getArtifactTitle());

            // Validate member exists before proceeding
            Member member = memberService.findById(dto.getMemberId());
            if (member == null) {
                log.error("Member not found with ID: {}", dto.getMemberId());
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            String filePath = String.format(
                    "%s/%s",
                    member.getDepartment(),
                    member.getDesignation()
            );

            String blobName = String.format(
                    "%s/%s",
                    filePath,
                    file.getOriginalFilename()
            );

            fileStorageCreator.create(blobName, file);

            Artifact artifact = artifactBuilder.build(dto, filePath, containerName, file.getOriginalFilename());
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
    @GetMapping("/find/all")
    public ResponseEntity<List<Map<String, String>>> findAllArtifactType() throws IOException {
        log.info("Fetching all available artifact types");

        List<Map<String, String>> types = Stream.of(ArtifactType.values())
                .map(type -> {
                    Map<String, String> typeInfo = new HashMap<>();
                    typeInfo.put("name", type.name());
                    typeInfo.put("displayName", type.getDisplayName());
                    return typeInfo;
                })
                .collect(Collectors.toList());

        log.info("Artifact types fetched successfully. Count: {}", types.size());

        return ResponseEntity.ok(types);
    }

    /**
     * Retrieves all artifacts for a specific member.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @return list of artifacts for the specified member
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<?> getArtifactsByMemberId(@PathVariable Long memberId) {
        try {
            List<Artifact> artifacts = artifactService.findByMemberId(memberId);
            return ResponseEntity.ok(artifacts);
        } catch (ServiceLayerException e) {
            log.error("Error fetching artifacts by memberId {}: {}", memberId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving artifacts: " + e.getMessage());
        }
    }

    /**
     * Retrieves artifacts for a specific member filtered by file path.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @param filePath the file path to filter artifacts by (passed as query parameter)
     * @return list of artifacts matching both member ID and file path
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    @GetMapping("/member/{memberId}/file")
    public ResponseEntity<?> getArtifactsByMemberIdAndFilePath(@PathVariable Long memberId,
                                                               @RequestParam String filePath) {
        try {
            List<Artifact> artifacts = artifactService.findByMemberIdAndFilePath(memberId, filePath);
            return ResponseEntity.ok(artifacts);
        } catch (ServiceLayerException e) {
            log.error("Error fetching artifacts by memberId {} and filePath '{}': {}", memberId, filePath, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving filtered artifacts: " + e.getMessage());
        }
    }
}
