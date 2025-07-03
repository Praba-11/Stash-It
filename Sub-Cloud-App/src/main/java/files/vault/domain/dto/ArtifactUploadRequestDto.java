package files.vault.domain.dto;

import files.vault.domain.entity.ArtifactType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for uploading an artifact associated with a member.
 *
 * <p>This class carries both the member's details and the artifact metadata
 * from the client-side form to the backend for further processing and persistence.
 */
@Getter
@Setter
public class ArtifactUploadRequestDto {

    /**
     * The member's first name.
     */
    private String firstName;

    /**
     * The member's last name.
     */
    private String lastName;

    /**
     * The email address of the member.
     */
    private String emailAddress;

    /**
     * The member's phone number.
     */
    private Long phoneNumber;

    /**
     * The unique roll number or employee ID of the member.
     */
    private String rollNo;

    /**
     * The department the member belongs to.
     */
    private String department;

    /**
     * The job title or position of the member.
     */
    private String designation;

    /**
     * The type of artifact (e.g., CERTIFICATE, BADGE, etc.).
     */
    private ArtifactType artifactType;

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
