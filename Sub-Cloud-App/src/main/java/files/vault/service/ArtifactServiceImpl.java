package files.vault.service;

import commons.exceptions.service.DaoLayerException;
import commons.exceptions.service.ServiceLayerException;
import files.vault.dao.ArtifactDao;
import files.vault.domain.entity.Artifact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtifactServiceImpl implements ArtifactService {

    @Autowired
    private ArtifactDao artifactDao;

    @Override
    public int create(Artifact artifact) {
        try {
            return artifactDao.create(artifact);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException(exception.getMessage());
        }
    }
}
