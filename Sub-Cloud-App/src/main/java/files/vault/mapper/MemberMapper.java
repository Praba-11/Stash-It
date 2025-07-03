package files.vault.mapper;

import files.vault.domain.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis Mapper interface for Member entity database operations.
 */
@Mapper
public interface MemberMapper {

    /**
     * Inserts a new member record into the database.
     *
     * <p>The generated primary key will be set into the {@code id} property of the member object.
     *
     * @param member the member entity to insert
     * @return the number of rows affected (usually 1)
     */
    @Insert("INSERT INTO member (roll_no, first_name, last_name, full_name, email, department, designation, phone_number) " +
            "VALUES (#{rollNo}, #{firstName}, #{lastName}, #{fullName}, #{email}, #{department}, #{designation}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Member member);

    /**
     * Selects members whose full name or roll number contains the given search string.
     *
     * <p>This performs a LIKE query with wildcards before and after the search string.
     *
     * @param chars the search string to match against full_name or roll_no
     * @return a list of members matching the criteria; empty list if none found
     */
    @Select("SELECT * FROM member WHERE full_name LIKE CONCAT('%', #{chars}, '%') OR roll_no LIKE CONCAT('%', #{chars}, '%')")
    List<Member> getByNameOrRollNo(@Param("chars") String chars);
}
