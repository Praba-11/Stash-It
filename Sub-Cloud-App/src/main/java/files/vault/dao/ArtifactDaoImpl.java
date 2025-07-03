package files.vault.dao;

import commons.exceptions.service.DaoLayerException;
import files.vault.domain.entity.Artifact;
import files.vault.mapper.ArtifactMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtifactDaoImpl implements ArtifactDao {

    @Autowired
    private ArtifactMapper artifactMapper;

    @Override
    public int create(Artifact artifact) {
        try {
            return artifactMapper.create(artifact);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }
}
