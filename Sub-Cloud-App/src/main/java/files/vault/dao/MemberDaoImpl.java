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

/**
 * Implementation of {@link MemberDao} interface using MyBatis mapper.
 *
 * <p>This class handles the data access logic for Member entities,
 * delegating calls to the {@link MemberMapper} and converting
 * persistence exceptions into DAO layer exceptions.
 */
@Component
public class MemberDaoImpl implements MemberDao {

    private static final Logger log = LoggerFactory.getLogger(MemberDaoImpl.class);

    @Autowired
    private MemberMapper mapper;

    /**
     * Creates a new member record in the database.
     *
     * @param member the member entity to create
     * @return the number of rows affected (typically 1)
     * @throws DaoLayerException if a persistence error occurs during insert
     */
    @Override
    public int create(Member member) {
        try {
            return mapper.create(member);
        } catch (PersistenceException exception) {
            log.error("PersistenceException while creating member: {}", exception.getMessage(), exception);
            throw new DaoLayerException(exception.getMessage());
        }
    }

    /**
     * Finds members by matching their name or roll number.
     *
     * @param chars the search string to match against name or roll number
     * @return list of matching members; empty list if none found
     * @throws DaoLayerException if a persistence error occurs during the query
     */
    @Override
    public List<Member> findByNameOrRollNo(String chars) {
        try {
            return mapper.getByNameOrRollNo(chars);
        } catch (PersistenceException exception) {
            log.error("PersistenceException while finding members by name or rollNo: {}", exception.getMessage(), exception);
            throw new DaoLayerException(exception.getMessage());
        }
    }
}
