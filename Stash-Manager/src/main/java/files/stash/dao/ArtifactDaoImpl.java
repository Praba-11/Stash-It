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
     * Retrieves an artifact using a composite key: member ID, container name, file path, and blob name.
     *
     * @param memberId      the member's ID
     * @param containerName the storage container name
     * @param filePath      the blob file path
     * @param blobName      the name of the blob file
     * @return the artifact entity, or {@code null} if not found
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    @Override
    public Artifact find(Long memberId, String containerName, String filePath, String blobName) {
        try {
            return artifactMapper.find(memberId, containerName, filePath, blobName);
        } catch (PersistenceException exception) {
            throw new DaoLayerException("Error fetching artifact by composite key: " + exception.getMessage(), exception);
        }
    }


    /**
     * Retrieves an artifact by its unique ID.
     *
     * @param id the artifact ID
     * @return the artifact entity, or {@code null} if not found
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
     * Retrieves all artifact records by member ID from the database.
     *
     * @param memberId the ID of the member whose artifacts are to be retrieved
     * @return a list of artifacts associated with the given member ID
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    @Override
    public List<Artifact> findByMemberId(Long memberId) {
        try {
            return artifactMapper.findByMemberId(memberId);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }

    /**
     * Retrieves artifact records by member ID and file path from the database.
     *
     * @param memberId the ID of the member
     * @param filePath the path of the artifact file
     * @return a list of matching artifacts
     * @throws DaoLayerException if a persistence error occurs during retrieval
     */
    @Override
    public List<Artifact> findByMemberIdAndFilePath(Long memberId, String filePath) {
        try {
            return artifactMapper.findByMemberIdAndFilePath(memberId, filePath);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }

    /**
     * Updates an existing artifact record in the database.
     *
     * @param artifact the artifact entity with updated values
     * @return the number of rows affected (usually 1)
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
     * @return the number of rows affected (usually 1)
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
