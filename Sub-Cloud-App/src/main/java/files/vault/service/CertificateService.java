package files.vault.service;

import files.vault.domain.entity.Certificate;
import files.vault.domain.entity.Member;

import java.util.List;

public interface CertificateService {

    int create(Certificate certificate);
}
