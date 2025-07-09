package files.stash.service;

import files.stash.domain.entity.Artifact;

/**
 * Service interface for handling operations related to {@link Artifact} entities.
 * <p>
 * Defines business logic contracts for creating and managing artifacts in the system.
 */
public interface ArtifactService {

    /**
     * Persists a new {@link Artifact} in the system.
     *
     * @param artifact the artifact entity to be created
     * @return an integer indicating the result of the creation operation (e.g., affected rows or generated ID)
     */
    int create(Artifact artifact);
}
