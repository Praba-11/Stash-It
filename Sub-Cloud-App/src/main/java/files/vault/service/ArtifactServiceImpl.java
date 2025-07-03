package files.vault.service;

import commons.exceptions.service.DaoLayerException;
import commons.exceptions.service.ServiceLayerException;
import files.vault.dao.ArtifactDao;
import files.vault.domain.entity.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new ServiceLayerException(exception.getMessage());
        }
    }
}
