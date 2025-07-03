package files.vault.component.service;

import files.vault.domain.dto.ArtifactUploadRequestDto;
import files.vault.domain.dto.MemberAndArtifactUploadRequestDto;
import files.vault.domain.entity.Artifact;
import files.vault.domain.entity.ArtifactType;
import files.vault.domain.entity.Department;
import files.vault.domain.entity.Member;

public class ArtifactBuilder {

    public Artifact build(MemberAndArtifactUploadRequestDto memberAndArtifactUploadRequestDto) {
        Artifact artifact = new Artifact();

        artifact.setType(ArtifactType.valueOf(memberAndArtifactUploadRequestDto.getArtifactType()));
        artifact.setTitle(memberAndArtifactUploadRequestDto.getArtifactTitle());
        artifact.setIssuer(memberAndArtifactUploadRequestDto.getIssuer());
        artifact.setDescription(memberAndArtifactUploadRequestDto.getDescription());
        artifact.setCreatedDate(memberAndArtifactUploadRequestDto.getCreatedDate());
        artifact.setExpiryDate(memberAndArtifactUploadRequestDto.getExpiryDate());

        return artifact;
    }

    public Artifact build(ArtifactUploadRequestDto artifactUploadRequestDto) {
        Artifact artifact = new Artifact();

        artifact.setType(ArtifactType.valueOf(artifactUploadRequestDto.getArtifactType()));
        artifact.setTitle(artifactUploadRequestDto.getArtifactTitle());
        artifact.setIssuer(artifactUploadRequestDto.getIssuer());
        artifact.setDescription(artifactUploadRequestDto.getDescription());
        artifact.setCreatedDate(artifactUploadRequestDto.getCreatedDate());
        artifact.setExpiryDate(artifactUploadRequestDto.getExpiryDate());

        return artifact;
    }
}
