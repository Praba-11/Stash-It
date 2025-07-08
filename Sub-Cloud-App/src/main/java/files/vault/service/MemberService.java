package files.vault.service;

import commons.exceptions.service.ServiceLayerException;
import files.vault.domain.entity.Member;

import java.util.List;

/**
 * Service interface for managing Member entities.
 */
public interface MemberService {

    /**
     * Creates a new member in the system.
     *
     * @param member the member entity to be created
     * @return the number of records created (typically 1 if successful)
     * @throws ServiceLayerException if an error occurs during creation
     */
    int create(Member member) throws ServiceLayerException;

    /**
     * Finds members whose name or roll number matches the given search string.
     *
     * <p>This method performs a case-insensitive search and returns
     * a list of matching members. It can be used for partial or full
     * matches on either name or roll number.
     *
     * @param chars the search string to match against member name or roll number
     * @return a list of members matching the search criteria; empty list if none found
     * @throws ServiceLayerException if an error occurs during the search
     */
    List<Member> findByNameOrRollNo(String chars) throws ServiceLayerException;

    /**
     * Retrieves a member by their unique identifier.
     *
     * <p>This method performs a direct lookup using the member ID and
     * returns the corresponding member if found. It is typically used
     * for precise access to a specific member's data (e.g., during artifact
     * uploads or profile edits).
     *
     * @param memberId the unique ID of the member to retrieve
     * @return the member with the given ID, or {@code null} if no such member exists
     * @throws ServiceLayerException if an error occurs during the lookup process
     */
    Member findById(Long memberId) throws ServiceLayerException;
}

