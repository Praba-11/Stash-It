package files.stash.component.service;

import files.stash.domain.dto.MemberAndArtifactUploadRequestDto;
import files.stash.domain.entity.Department;
import files.stash.domain.entity.Member;

/**
 * Utility class responsible for building {@link Member} entities from incoming DTOs.
 * <p>
 * This builder is used to transform client-facing data transfer objects into domain entities
 * for internal application use.
 */
public class MemberBuilder {

    /**
     * Builds a {@link Member} entity using data from a {@link MemberAndArtifactUploadRequestDto}.
     *
     * @param memberAndArtifactUploadRequestDto DTO containing member and artifact data.
     * @return a populated {@link Member} entity based on the member-related fields from the DTO.
     * @throws IllegalArgumentException if the department value is not a valid {@link Department} enum.
     */
    public Member build(MemberAndArtifactUploadRequestDto memberAndArtifactUploadRequestDto) {
        Member member = new Member();

        member.setRollNo(memberAndArtifactUploadRequestDto.getRollNo());
        member.setFirstName(memberAndArtifactUploadRequestDto.getFirstName());
        member.setLastName(memberAndArtifactUploadRequestDto.getLastName());
        member.setFullName(String.format("%s %s",
                memberAndArtifactUploadRequestDto.getFirstName(),
                memberAndArtifactUploadRequestDto.getLastName()));
        member.setPhoneNumber(memberAndArtifactUploadRequestDto.getPhoneNumber());
        member.setDepartment(Department.valueOf(memberAndArtifactUploadRequestDto.getDepartment()));
        member.setDesignation(memberAndArtifactUploadRequestDto.getDesignation());
        member.setEmailAddress(memberAndArtifactUploadRequestDto.getEmailAddress());

        return member;
    }
}
