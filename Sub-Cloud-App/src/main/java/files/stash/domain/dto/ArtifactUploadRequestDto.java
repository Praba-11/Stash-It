package files.stash.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for uploading an artifact.
 *
 * <p>This class carries both the member's details and the artifact metadata
 * from the client-side form to the backend for further processing and persistence.
 */
@Getter
@Setter
public class ArtifactUploadRequestDto {

    /**
     * The Member ID.
     */
    private Long memberId;

    /**
     * The type of artifact (e.g., CERTIFICATE, BADGE, etc.).
     */
    private String artifactType;

    /**
     * The title of the artifact (e.g., "Java Training Completion Certificate").
     */
    private String artifactTitle;

    /**
     * The date on which the artifact was created or issued.
     */
    private Date createdDate;

    /**
     * The date on which the artifact expires, if applicable.
     */
    private Date expiryDate;

    /**
     * The issuer of the artifact (e.g., "Oracle", "Coursera").
     */
    private String issuer;

    /**
     * A brief description of the artifact and its significance.
     */
    private String description;
}
