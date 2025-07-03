package files.vault.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Artifact {

    private ArtifactType type;

    private String title;

    private Date createdDate;

    private Date expiryDate;

    private String issuer;

    private String description;
}
