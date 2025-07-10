package files.stash.dao;

import files.stash.domain.entity.Member;
import service.DaoLayerException;

import java.util.List;

/**
 * Data Access Object (DAO) interface for Member entity operations.
 */
public interface MemberDao {

    /**
     * Inserts a new member record into the database.
     *
     * @param member the member entity to insert
     * @return number of rows affected (typically 1 if successful)
     * @throws DaoLayerException if a database access error occurs
     */
    int create(Member member) throws DaoLayerException;

    /**
     * Finds members by matching their name or roll number with the provided string.
     *
     * <p>This method supports partial matches and returns all members
     * whose name or roll number contains the search string.
     *
     * @param chars the search string to match against name or roll number
     * @return list of matching members, or empty list if none found
     * @throws DaoLayerException if a database access error occurs
     */
    List<Member> findByNameOrRollNo(String chars) throws DaoLayerException;

    /**
     * Finds a member by their unique identifier.
     *
     * <p>This method performs an exact match on the member ID and returns
     * the corresponding member entity from the database.
     *
     * @param memberId the unique ID of the member
     * @return the matching member entity, or {@code null} if no member is found
     * @throws DaoLayerException if a database access error occurs
     */
    Member findById(Long memberId) throws DaoLayerException;

}
