package files.stash.dao;

import files.stash.domain.entity.Artifact;

import java.util.List;

/**
 * Data access interface for Artifact entity operations.
 */
public interface ArtifactDao {

    /**
     * Persists a new artifact in the database.
     *
     * @param artifact the artifact to be created
     * @return number of rows affected
     */
    int create(Artifact artifact);

    /**
     * Retrieves an artifact by its ID.
     *
     * @param id the ID of the artifact
     * @return the found artifact or null if not found
     */
    Artifact findById(Long id);

    /**
     * Retrieves all artifacts from the database.
     *
     * @return list of artifacts
     */
    List<Artifact> findAll();

    /**
     * Updates an existing artifact in the database.
     *
     * @param artifact the artifact with updated fields
     * @return number of rows affected
     */
    int update(Artifact artifact);

    /**
     * Deletes an artifact by its ID.
     *
     * @param id the ID of the artifact to be deleted
     * @return number of rows affected
     */
    int delete(Long id);
}
