package files.vault.component.service;

import files.vault.domain.dto.MemberAndArtifactUploadRequestDto;
import files.vault.domain.entity.Department;
import files.vault.domain.entity.Member;

public class MemberBuilder {

    public Member build(MemberAndArtifactUploadRequestDto memberAndArtifactUploadRequestDto) {
        Member member = new Member();

        member.setRollNo(memberAndArtifactUploadRequestDto.getRollNo());
        member.setFirstName(memberAndArtifactUploadRequestDto.getFirstName());
        member.setLastName(memberAndArtifactUploadRequestDto.getLastName());
        member.setFullName(String.format("%s %s", memberAndArtifactUploadRequestDto.getFirstName() , memberAndArtifactUploadRequestDto.getLastName()));
        member.setPhoneNumber(memberAndArtifactUploadRequestDto.getPhoneNumber());
        member.setDepartment(Department.valueOf(memberAndArtifactUploadRequestDto.getDepartment()));
        member.setDesignation(memberAndArtifactUploadRequestDto.getDesignation());
        member.setEmailAddress(memberAndArtifactUploadRequestDto.getEmailAddress());

        return member;
    }
}
