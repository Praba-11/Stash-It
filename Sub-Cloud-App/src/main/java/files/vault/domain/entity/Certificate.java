package files.vault.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Certificate {

    private String title;

    private CertificateType type;

    private Date issuedOn;

    private Date expiredOn;

    private String organization;

    private String description;
}
