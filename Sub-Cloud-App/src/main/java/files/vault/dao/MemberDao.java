package files.vault.dao;

import commons.exceptions.service.DaoLayerException;
import files.vault.domain.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberDao {

    int create(Member member) throws DaoLayerException;

    List<Member> getByNameOrRollNo(String chars) throws DaoLayerException;
}
