package files.vault.service;

import commons.exceptions.service.DaoLayerException;
import commons.exceptions.service.ServiceLayerException;
import files.vault.dao.MemberDao;
import files.vault.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link MemberService} interface to manage member operations.
 *
 * <p>This service delegates persistence operations to the {@link MemberDao} and
 * translates DAO exceptions into service-layer exceptions.
 */
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao dao;

    /**
     * Creates a new member by delegating to the DAO layer.
     *
     * @param member the member entity to create
     * @return the number of records created (usually 1 if successful)
     * @throws ServiceLayerException if any DAO exception occurs during creation
     */
    @Override
    public int create(Member member) {
        try {
            return dao.create(member);
        } catch (DaoLayerException exception) {
            log.error("Failed to create member: {}", exception.getMessage(), exception);
            throw new ServiceLayerException(exception.getMessage());
        }
    }

    /**
     * Finds members by name or roll number by delegating to the DAO layer.
     *
     * @param chars the search string for member name or roll number
     * @return list of matching members; empty list if none found
     * @throws ServiceLayerException if any DAO exception occurs during search
     */
    @Override
    public List<Member> findByNameOrRollNo(String chars) {
        try {
            return dao.findByNameOrRollNo(chars);
        } catch (DaoLayerException exception) {
            log.error("Failed to find members by name or roll number: {}", exception.getMessage(), exception);
            throw new ServiceLayerException(exception.getMessage());
        }
    }

    /**
     * Finds a member by their unique ID by delegating to the DAO layer.
     *
     * @param memberId the unique identifier of the member
     * @return the member if found; {@code null} if no member exists with the given ID
     * @throws ServiceLayerException if any DAO exception occurs during lookup
     */
    @Override
    public Member findById(Long memberId) {
        try {
            return dao.findById(memberId);
        } catch (DaoLayerException exception) {
            log.error("Failed to find member by ID {}: {}", memberId, exception.getMessage(), exception);
            throw new ServiceLayerException(exception.getMessage());
        }
    }
}
