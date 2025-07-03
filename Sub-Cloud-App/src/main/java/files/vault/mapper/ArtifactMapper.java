package files.vault.mapper;

import files.vault.domain.entity.Artifact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ArtifactMapper {

    @Insert("INSERT INTO artifact (title, type, issued_on, expired_on, organization, description) " +
            "VALUES (#{title}, #{type}, #{issuedOn}, #{expiredOn}, #{organization}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Artifact artifact);

}
