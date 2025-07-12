package files.stash.domain.dto;

import files.stash.domain.entity.ArtifactType;
import lombok.Getter;
import lombok.Setter;


/**
 * Data Transfer Object (DTO) for creating a new member.
 *
 * <p>This class carries member details submitted from the client-side form
 * to the backend for persistence in the database.
 */
@Getter
@Setter
public class ArtifactGetRequestDto {

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

    private ArtifactType artifactType;
}

