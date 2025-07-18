package files.stash.service;

import files.stash.domain.entity.Artifact;
import service.ServiceLayerException;

import java.util.List;

/**
 * Service interface for handling operations related to {@link Artifact} entities.
 *
 * <p>Defines business logic contracts for creating and managing artifacts in the system.
 */
public interface ArtifactService {

    /**
     * Persists a new {@link Artifact} in the system.
     *
     * @param artifact the artifact entity to be created
     * @return an integer indicating the result of the creation operation (usually 1 if successful)
     * @throws ServiceLayerException if an error occurs during the creation process
     */
    int create(Artifact artifact) throws ServiceLayerException;

    /**
     * Retrieves a specific {@link Artifact} by its unique identifier.
     *
     * @param id the ID of the artifact
     * @return the artifact entity, or {@code null} if not found
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    Artifact findById(Long id) throws ServiceLayerException;

    /**
     * Retrieves all {@link Artifact} records in the system.
     *
     * @return a list of artifact entities
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    List<Artifact> findAll() throws ServiceLayerException;

    /**
     * Retrieves all {@link Artifact} records by member ID in the system.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @return a list of artifact entities associated with the given member ID
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    List<Artifact> findByMemberId(Long memberId) throws ServiceLayerException;

    /**
     * Retrieves all {@link Artifact} records by member ID and file path in the system.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @param filePath the file path of the artifact to filter by
     * @return a list of artifacts matching the given member ID and file path
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    List<Artifact> findByMemberIdAndFilePath(Long memberId, String filePath) throws ServiceLayerException;

    /**
     * Updates an existing {@link Artifact}.
     *
     * @param artifact the artifact entity with updated information
     * @return an integer indicating the result of the update operation (usually 1)
     * @throws ServiceLayerException if an error occurs during the update process
     */
    int update(Artifact artifact) throws ServiceLayerException;

    /**
     * Deletes a specific {@link Artifact} by its ID.
     *
     * @param id the ID of the artifact to delete
     * @return an integer indicating the result of the delete operation (usually 1)
     * @throws ServiceLayerException if an error occurs during the deletion process
     */
    int delete(Long id) throws ServiceLayerException;
}
