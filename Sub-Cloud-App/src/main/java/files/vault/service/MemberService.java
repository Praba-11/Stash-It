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
}

