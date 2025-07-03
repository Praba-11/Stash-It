package files.vault.dao;

import commons.exceptions.service.DaoLayerException;
import files.vault.domain.entity.Member;

import java.util.List;

public interface MemberDao {

    int create(Member member) throws DaoLayerException;

    List<Member> findByNameOrRollNo(String chars) throws DaoLayerException;
}
