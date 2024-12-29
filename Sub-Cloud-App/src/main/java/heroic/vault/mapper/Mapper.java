package heroic.vault.mapper;

import org.apache.ibatis.annotations.Select;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {

    @Select("SELECT info FROM information WHERE roll = #{roll}")
    String getInfo(Long roll);

}
