package files.vault.mapper;

import org.apache.ibatis.annotations.Select;

@org.apache.ibatis.annotations.Mapper
public interface MemberMapper {

    @Select("SELECT * FROM member WHERE roll_no = #{rollNo}")
    String getInfo(String rollNo);

}
