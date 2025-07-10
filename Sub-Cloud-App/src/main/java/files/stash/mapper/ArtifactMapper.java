package files.stash.mapper;

import files.stash.domain.entity.Artifact;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * MyBatis Mapper interface for CRUD operations on Artifact entity.
 */
@Mapper
public interface ArtifactMapper {

    /**
     * Inserts a new artifact record into the database.
     * The generated primary key will be set into the {@code id} property of the artifact object.
     *
     * @param artifact the artifact entity to insert
     * @return the number of rows affected (typically 1)
     */
    @Insert("INSERT INTO artifact (title, type, issued_on, expired_on, organization, description) " +
            "VALUES (#{title}, #{type}, #{issuedOn}, #{expiredOn}, #{organization}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Artifact artifact);

    /**
     * Retrieves an artifact by its unique ID.
     *
     * @param id the unique identifier of the artifact
     * @return the artifact entity matching the given ID, or null if not found
     */
    @Select("SELECT id, title, type, issued_on, expired_on, organization, description FROM artifact WHERE id = #{id}")
    Artifact findById(Long id);

    /**
     * Retrieves all artifacts from the database.
     *
     * @return a list of all artifact entities
     */
    @Select("SELECT id, title, type, issued_on, expired_on, organization, description FROM artifact")
    List<Artifact> findAll();

    /**
     * Updates an existing artifact record identified by its ID.
     *
     * @param artifact the artifact entity with updated fields
     * @return the number of rows affected (typically 1)
     */
    @Update("UPDATE artifact SET title = #{title}, type = #{type}, issued_on = #{issuedOn}, expired_on = #{expiredOn}, " +
            "organization = #{organization}, description = #{description} WHERE id = #{id}")
    int update(Artifact artifact);

    /**
     * Deletes an artifact record identified by its ID.
     *
     * @param id the unique identifier of the artifact to delete
     * @return the number of rows affected (typically 1)
     */
    @Delete("DELETE FROM artifact WHERE id = #{id}")
    int delete(Long id);
}
