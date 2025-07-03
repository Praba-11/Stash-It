package files.vault.component.service;

import files.vault.domain.dto.ArtifactUploadRequestDto;
import files.vault.domain.entity.Department;
import files.vault.domain.entity.Member;

public class MemberBuilder {

    public Member build(ArtifactUploadRequestDto artifactUploadRequestDto) {
        Member member = new Member();

        member.setRollNo(artifactUploadRequestDto.getRollNo());
        member.setFirstName(artifactUploadRequestDto.getFirstName());
        member.setLastName(artifactUploadRequestDto.getLastName());
        member.setFullName(String.format("%s %s", artifactUploadRequestDto.getFirstName() , artifactUploadRequestDto.getLastName()));
        member.setPhoneNumber(artifactUploadRequestDto.getPhoneNumber());
        member.setDepartment(Department.valueOf(artifactUploadRequestDto.getDepartment()));
        member.setDesignation(artifactUploadRequestDto.getDesignation());
        member.setEmailAddress(artifactUploadRequestDto.getEmailAddress());

        return member;
    }
}
