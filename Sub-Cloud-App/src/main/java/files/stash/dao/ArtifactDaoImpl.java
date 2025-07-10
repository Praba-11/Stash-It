package files.stash.dao;

import files.stash.domain.entity.Artifact;
import files.stash.mapper.ArtifactMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.DaoLayerException;

import java.util.List;

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

    /**
     * Retrieves an artifact by its unique ID.
     *
     * @param id the artifact ID
     * @return the artifact entity, or null if not found
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    @Override
    public Artifact findById(Long id) {
        try {
            return artifactMapper.findById(id);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }

    /**
     * Retrieves all artifact records from the database.
     *
     * @return a list of all artifacts
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    @Override
    public List<Artifact> findAll() {
        try {
            return artifactMapper.findAll();
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }

    /**
     * Updates an existing artifact record in the database.
     *
     * @param artifact the artifact entity with updated values
     * @return the number of rows affected
     * @throws DaoLayerException if a persistence error occurs during update
     */
    @Override
    public int update(Artifact artifact) {
        try {
            return artifactMapper.update(artifact);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }

    /**
     * Deletes an artifact record by its ID.
     *
     * @param id the ID of the artifact to delete
     * @return the number of rows affected
     * @throws DaoLayerException if a persistence error occurs during deletion
     */
    @Override
    public int delete(Long id) {
        try {
            return artifactMapper.delete(id);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }
}
