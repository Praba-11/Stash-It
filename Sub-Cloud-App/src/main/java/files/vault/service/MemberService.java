package files.vault.service;

import files.vault.domain.entity.Member;

import java.util.List;

public interface MemberService {

    int create(Member member);

    List<Member> getByNameOrRollNo(String chars);
}
