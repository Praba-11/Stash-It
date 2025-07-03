package files.vault.mapper;

import files.vault.domain.entity.Artifact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * MyBatis Mapper interface for Artifact entity database operations.
 */
@Mapper
public interface ArtifactMapper {

    /**
     * Inserts a new artifact record into the database.
     *
     * <p>The generated primary key will be set into the {@code id} property of the artifact object.
     *
     * @param artifact the artifact entity to insert
     * @return the number of rows affected (typically 1)
     */
    @Insert("INSERT INTO artifact (title, type, issued_on, expired_on, organization, description) " +
            "VALUES (#{title}, #{type}, #{issuedOn}, #{expiredOn}, #{organization}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Artifact artifact);
}
