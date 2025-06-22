package files.vault.dao;

import files.vault.domain.entity.Certificate;
import files.vault.domain.entity.Member;

import java.util.List;

public interface CertificateDao {

    int create(Certificate certificate);
}
