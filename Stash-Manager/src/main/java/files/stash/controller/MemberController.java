package files.stash.controller;

import files.stash.component.service.ArtifactBuilder;
import files.stash.component.service.FileStorageCreator;
import files.stash.component.service.MemberBuilder;
import files.stash.domain.dto.MemberAndArtifactUploadRequestDto;
import files.stash.domain.entity.Artifact;
import files.stash.domain.entity.Department;
import files.stash.domain.entity.Member;
import files.stash.service.ArtifactService;
import files.stash.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ArtifactService artifactService;

    @Autowired
    private MemberBuilder memberBuilder;

    @Autowired
    private ArtifactBuilder artifactBuilder;

    @Autowired
    private FileStorageCreator fileStorageCreator;


    /**
     * Creates a new member with the provided details.
     *
     * <p>This endpoint accepts a member artifact upload request which includes
     * personal details and artifact metadata, builds a Member entity from it,
     * and persists it to the database.
     *
     * @param dto the artifact upload request DTO containing member information
     * @return {@link ResponseEntity} with HTTP 201 (Created) if successful
     */
    @PostMapping("/new/upload")
    public ResponseEntity<Void> createMemberAndUploadArtifact(@RequestParam MultipartFile file, @RequestBody MemberAndArtifactUploadRequestDto dto) {

        try {
            log.info("Member create initiated. Name: {} Roll no: {}", dto.getFirstName(), dto.getRollNo());

            Member member = memberBuilder.build(dto);
            memberService.create(member);

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
     * Retrieves a list of members matching the given name or roll number.
     *
     * <p>This endpoint performs a case-insensitive search using the given query string
     * and returns a list of matching members. If no parameter is provided, it returns
     * HTTP 400 Bad Request. If no match is found, it returns HTTP 404 Not Found.
     *
     * @param chars the partial or full name or roll number used for searching (required)
     * @return {@link ResponseEntity} containing:
     *         <ul>
     *             <li>HTTP 200 OK with list of matched members if found</li>
     *             <li>HTTP 400 BAD_REQUEST if query parameter is missing</li>
     *             <li>HTTP 404 NOT_FOUND if no matching member exists</li>
     *         </ul>
     */
    @GetMapping("/find")
    public ResponseEntity<List<Member>> getMemberByNameOrRollNo(@RequestParam(required = false) String chars) {
        try {
            if (chars == null) {
                log.warn("Missing query parameter: 'chars'");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            log.info("Member search initiated. Query: {}", chars);
            List<Member> members = memberService.findByNameOrRollNo(chars);

            if (members != null && !members.isEmpty()) {
                return new ResponseEntity<>(members, HttpStatus.OK);
            } else {
                log.info("No members found for query: {}", chars);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("Error occurred while searching for members: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all available departments defined for the member.
     *
     * <p>This endpoint returns a list of all {@link Department} enum values,
     * formatted as key-value pairs for use in dropdowns or selection UIs.
     *
     * @return HTTP 200 OK with list of department names and their formatted display names
     * @throws IOException if an unexpected I/O error occurs (reserved for future extension)
     *
     * <p><b>Sample Response:</b>
     * <pre>
     * [
     *   { "name": "INFORMATION_TECHNOLOGY", "displayName": "Information Technology" },
     *   { "name": "HUMAN_RESOURCE", "displayName": "Human Resource" },
     *   ...
     * ]
     * </pre>
     */
    @GetMapping("/find/departments")
    public ResponseEntity<List<Map<String, String>>> findAllDepartmentTypes() {
        try {
            log.info("Fetching all member departments");

            List<Map<String, String>> departments = Stream.of(Department.values())
                    .map(dept -> {
                        Map<String, String> deptInfo = new HashMap<>();
                        deptInfo.put("name", dept.name());

                        String displayName = dept.name().toLowerCase().replace('_', ' ');
                        displayName = Character.toUpperCase(displayName.charAt(0)) + displayName.substring(1);
                        deptInfo.put("displayName", displayName);

                        return deptInfo;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(departments);

        } catch (Exception e) {
            log.error("Failed to fetch department types: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

