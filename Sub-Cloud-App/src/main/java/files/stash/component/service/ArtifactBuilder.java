package files.stash.component.service;

import files.stash.domain.dto.ArtifactUploadRequestDto;
import files.stash.domain.dto.MemberAndArtifactUploadRequestDto;
import files.stash.domain.entity.Artifact;
import files.stash.domain.entity.ArtifactType;

/**
 * Utility class responsible for constructing {@link Artifact} entities from different DTOs.
 * <p>
 * This builder facilitates the conversion of data transfer objects (DTOs) received from the client
 * into {@link Artifact} entities used in the application’s domain layer.
 */
public class ArtifactBuilder {

    /**
     * Builds an {@link Artifact} entity using data from {@link MemberAndArtifactUploadRequestDto}.
     *
     * @param memberAndArtifactUploadRequestDto DTO that contains both member and artifact information.
     * @return a populated {@link Artifact} entity based on the artifact portion of the DTO.
     * @throws IllegalArgumentException if the artifact type provided is not a valid enum constant.
     */
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

    /**
     * Builds an {@link Artifact} entity using data from {@link ArtifactUploadRequestDto}.
     *
     * @param artifactUploadRequestDto DTO that contains artifact information.
     * @return a populated {@link Artifact} entity.
     * @throws IllegalArgumentException if the artifact type provided is not a valid enum constant.
     */
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
