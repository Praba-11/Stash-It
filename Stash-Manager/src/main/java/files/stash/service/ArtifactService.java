package files.stash.service;

import files.stash.domain.entity.Artifact;

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
     */
    int create(Artifact artifact);

    /**
     * Retrieves a specific {@link Artifact} by its unique identifier.
     *
     * @param id the ID of the artifact
     * @return the artifact entity, or {@code null} if not found
     */
    Artifact findById(Long id);

    /**
     * Retrieves all {@link Artifact} records in the system.
     *
     * @return a list of artifact entities
     */
    List<Artifact> findAll();

    /**
     * Updates an existing {@link Artifact}.
     *
     * @param artifact the artifact entity with updated information
     * @return an integer indicating the result of the update operation (usually 1)
     */
    int update(Artifact artifact);

    /**
     * Deletes a specific {@link Artifact} by its ID.
     *
     * @param id the ID of the artifact to delete
     * @return an integer indicating the result of the delete operation (usually 1)
     */
    int delete(Long id);
}
