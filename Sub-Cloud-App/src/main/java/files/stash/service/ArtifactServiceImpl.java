package files.stash.service;

import files.stash.dao.ArtifactDao;
import files.stash.domain.entity.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DaoLayerException;
import service.ServiceLayerException;

import java.util.List;

/**
 * Implementation of {@link ArtifactService} interface for managing artifact operations.
 *
 * <p>This service delegates persistence tasks to the {@link ArtifactDao}
 * and translates DAO exceptions into service-layer exceptions.
 */
@Service
public class ArtifactServiceImpl implements ArtifactService {

    @Autowired
    private ArtifactDao artifactDao;

    /**
     * Creates a new artifact record in the system.
     *
     * @param artifact the artifact entity to create
     * @return the number of records created (usually 1 if successful)
     * @throws ServiceLayerException if an error occurs during the persistence operation
     */
    @Override
    public int create(Artifact artifact) {
        try {
            return artifactDao.create(artifact);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException("Failed to create artifact", exception);
        }
    }

    /**
     * Retrieves an artifact by its ID.
     *
     * @param id the ID of the artifact
     * @return the retrieved artifact, or null if not found
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    @Override
    public Artifact findById(Long id) {
        try {
            return artifactDao.findById(id);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException("Failed to fetch artifact by ID", exception);
        }
    }

    /**
     * Retrieves all artifact records in the system.
     *
     * @return list of all artifacts
     * @throws ServiceLayerException if an error occurs during retrieval
     */
    @Override
    public List<Artifact> findAll() {
        try {
            return artifactDao.findAll();
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException("Failed to fetch artifact list", exception);
        }
    }

    /**
     * Updates an existing artifact in the system.
     *
     * @param artifact the artifact with updated values
     * @return the number of records updated (usually 1)
     * @throws ServiceLayerException if an error occurs during the update
     */
    @Override
    public int update(Artifact artifact) {
        try {
            return artifactDao.update(artifact);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException("Failed to update artifact", exception);
        }
    }

    /**
     * Deletes an artifact by its ID.
     *
     * @param id the ID of the artifact to delete
     * @return the number of records deleted (usually 1)
     * @throws ServiceLayerException if an error occurs during the deletion
     */
    @Override
    public int delete(Long id) {
        try {
            return artifactDao.delete(id);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException("Failed to delete artifact", exception);
        }
    }
}
