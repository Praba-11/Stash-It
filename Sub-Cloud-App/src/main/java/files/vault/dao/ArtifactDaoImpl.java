package files.vault.dao;

import commons.exceptions.service.DaoLayerException;
import files.vault.domain.entity.Artifact;
import files.vault.mapper.ArtifactMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link ArtifactDao} interface to manage Artifact persistence operations.
 *
 * <p>This class delegates CRUD operations to the MyBatis {@link ArtifactMapper} and
 * handles persistence exceptions by wrapping them in {@link DaoLayerException}.
 */
@Component
public class ArtifactDaoImpl implements ArtifactDao {

    @Autowired
    private ArtifactMapper artifactMapper;

    /**
     * Creates a new artifact record in the database.
     *
     * @param artifact the artifact entity to persist
     * @return the number of rows affected (usually 1)
     * @throws DaoLayerException if a persistence error occurs during creation
     */
    @Override
    public int create(Artifact artifact) {
        try {
            return artifactMapper.create(artifact);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }
}
