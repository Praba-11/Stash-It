package files.vault.mapper;

import files.vault.domain.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member (roll_no, first_name, last_name, full_name, email, department, designation, phone_number) " +
            "VALUES (#{rollNo}, #{firstName}, #{lastName}, #{fullName}, #{email}, #{department}, #{designation}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Member member);


    @Select("SELECT * FROM member WHERE full_name LIKE CONCAT('%', #{chars}, '%') OR roll_no LIKE CONCAT('%', #{chars}, '%')")
    List<Member> getByNameOrRollNo(@Param("chars") String chars);


}
