package files.stash.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Represents an artifact entity containing metadata such as type, title, issuer,
 * creation and expiry dates, and a description.
 * <p>
 * This entity is used throughout the system to encapsulate details of an uploaded artifact
 * like certificates, awards, or documents.
 */
@Getter
@Setter
public class Artifact {

    /**
     * The unique identifier of the artifact.
     */
    private Long id;

    /**
     * The ID of the member associated with this artifact.
     */
    private Long memberId;

    /**
     * The file name.
     */
    private String name;

    /**
     * The type of the artifact (e.g., CERTIFICATE, LICENSE, AWARD).
     */
    private ArtifactType type;

    /**
     * The title or name of the artifact.
     */
    private String title;

    /**
     * The file path where the artifact is stored.
     */
    private String filePath;

    /**
     * The container where the artifact is stored.
     */
    private String containerName;

    /**
     * The date the artifact was created or issued.
     */
    private Date createdDate;

    /**
     * The date the artifact expires, if applicable.
     */
    private Date expiryDate;

    /**
     * The name of the organization or individual that issued the artifact.
     */
    private String issuer;

    /**
     * A brief description or summary of the artifact.
     */
    private String description;
}
