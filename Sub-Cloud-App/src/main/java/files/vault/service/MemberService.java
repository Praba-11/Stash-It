package files.vault.service;

import commons.exceptions.service.ServiceLayerException;
import files.vault.domain.entity.Member;

import java.util.List;

public interface MemberService {

    int create(Member member) throws ServiceLayerException;

    List<Member> findByNameOrRollNo(String chars) throws ServiceLayerException;
}
