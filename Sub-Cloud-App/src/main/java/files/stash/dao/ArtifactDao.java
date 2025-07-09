package files.stash.dao;

import files.stash.domain.entity.Artifact;

public interface ArtifactDao {

    int create(Artifact artifact);
}
