package files.vault.component.service;

import files.vault.domain.dto.MemberRequestDto;
import files.vault.domain.entity.Department;
import files.vault.domain.entity.Member;

public class MemberBuilder {

    public Member build(MemberRequestDto memberRequestDto) {
        Member member = new Member();

        member.setRollNo(memberRequestDto.getRollNo());
        member.setFirstName(memberRequestDto.getFirstName());
        member.setLastName(memberRequestDto.getLastName());
        member.setFullName(String.format("%s %s", memberRequestDto.getFirstName() ,memberRequestDto.getLastName()));
        member.setPhoneNumber(memberRequestDto.getPhoneNumber());
        member.setDepartment(Department.valueOf(memberRequestDto.getDepartment()));
        member.setDesignation(memberRequestDto.getDesignation());
        member.setEmailAddress(memberRequestDto.getEmailAddress());

        return member;
    }
}
