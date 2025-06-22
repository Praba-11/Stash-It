package files.vault.dao;

import commons.exceptions.service.DaoLayerException;
import files.vault.domain.entity.Member;
import files.vault.mapper.MemberMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberDaoImpl implements MemberDao {

    private static final Logger log = LoggerFactory.getLogger(MemberDaoImpl.class);
    @Autowired
    private MemberMapper mapper;

    @Override
    public int create(Member member) {
        try {
            return mapper.create(member);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }

    @Override
    public List<Member> getByNameOrRollNo(String chars) {
        try {
            return mapper.getByNameOrRollNo(chars);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }
}
