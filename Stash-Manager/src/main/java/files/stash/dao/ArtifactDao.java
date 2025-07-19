package files.stash.dao;

import files.stash.domain.entity.Artifact;
import service.DaoLayerException;

import java.util.List;

/**
 * Data access interface for {@link Artifact} entity operations.
 *
 * <p>Provides methods to perform CRUD operations and custom queries
 * related to artifact persistence.
 */
public interface ArtifactDao {

    /**
     * Persists a new {@link Artifact} in the database.
     *
     * @param artifact the artifact to be created
     * @return number of rows affected (usually 1 if successful)
     * @throws DaoLayerException if a persistence error occurs
     */
    int create(Artifact artifact) throws DaoLayerException;

    /**
     * Retrieves a specific {@link Artifact} using a composite key of member ID, container name, file path, and blob name.
     *
     * @param memberId the ID of the member who owns the artifact
     * @param containerName the Azure container name where the artifact is stored
     * @param filePath the relative path used to store the blob (e.g., "HR/Manager")
     * @param blobName the name of the blob file (e.g., "resume.pdf")
     * @return the found artifact, or {@code null} if not found
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    Artifact find(Long memberId, String containerName, String filePath, String blobName) throws DaoLayerException;


    /**
     * Retrieves a specific {@link Artifact} by its ID.
     *
     * @param id the ID of the artifact
     * @return the found artifact, or {@code null} if not found
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    Artifact findById(Long id) throws DaoLayerException;

    /**
     * Retrieves all {@link Artifact} records from the database.
     *
     * @return a list of all artifacts
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    List<Artifact> findAll() throws DaoLayerException;

    /**
     * Retrieves all {@link Artifact} records associated with a specific member ID.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @return a list of artifacts linked to the given member ID
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    List<Artifact> findByMemberId(Long memberId) throws DaoLayerException;

    /**
     * Retrieves all {@link Artifact} records matching a given member ID and file path.
     *
     * @param memberId the ID of the member
     * @param filePath the file path of the artifact
     * @return a list of artifacts that match both the member ID and file path
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    List<Artifact> findByMemberIdAndFilePath(Long memberId, String filePath) throws DaoLayerException;

    /**
     * Updates an existing {@link Artifact} record in the database.
     *
     * @param artifact the artifact entity with updated values
     * @return number of rows affected (usually 1)
     * @throws DaoLayerException if a persistence error occurs during the update
     */
    int update(Artifact artifact) throws DaoLayerException;

    /**
     * Deletes a specific {@link Artifact} record by its ID.
     *
     * @param id the ID of the artifact to delete
     * @return number of rows affected (usually 1)
     * @throws DaoLayerException if a persistence error occurs during the deletion
     */
    int delete(Long id) throws DaoLayerException;
}
