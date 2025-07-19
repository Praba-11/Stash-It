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
    @Insert("INSERT INTO artifact (member_id, name, title, type, file_path, container_name, issued_on, expired_on, organization, description) " +
            "VALUES (#{memberId}, #{name}, #{title}, #{type}, #{filePath}, #{containerName}, #{issuedOn}, #{expiredOn}, #{organization}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Artifact artifact);

    /**
     * Retrieves an artifact by member ID, container name, file path, and blob name.
     *
     * @param memberId      the ID of the associated member
     * @param containerName the Azure storage container name
     * @param filePath      the structured file path (e.g., "HR/Manager")
     * @param blobName      the name of the blob file (e.g., "resume.pdf")
     * @return the artifact matching the specified parameters, or null if not found
     */
    @Select("SELECT id, title, type, issued_on, expired_on, organization, description, member_id, container_name, file_path, blob_name FROM artifact " +
            "WHERE member_id = #{memberId} AND container_name = #{containerName} AND file_path = #{filePath} AND blob_name = #{blobName}")
    Artifact find(Long memberId, String containerName, String filePath, String blobName);


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
     * Retrieves all artifacts associated with a specific member ID.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @return a list of artifact entities linked to the given member ID
     */
    @Select("SELECT id, title, type, issued_on, expired_on, organization, description FROM artifact WHERE member_id = #{memberId}")
    List<Artifact> findByMemberId(Long memberId);

    /**
     * Retrieves artifacts associated with a specific member ID and file path.
     *
     * @param memberId the ID of the member
     * @param filePath the path of the artifact file
     * @return a list of artifacts matching the given member ID and file path
     */
    @Select("SELECT id, title, type, issued_on, expired_on, organization, description, file_path " +
            "FROM artifact WHERE member_id = #{memberId} AND file_path = #{filePath}")
    List<Artifact> findByMemberIdAndFilePath(@Param("memberId") Long memberId, @Param("filePath") String filePath);


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
