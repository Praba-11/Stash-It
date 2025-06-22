package files.vault.dao;

import commons.exceptions.service.DaoLayerException;
import files.vault.domain.entity.Certificate;
import files.vault.mapper.CertificateMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateDaoImpl implements CertificateDao {

    @Autowired
    private CertificateMapper certificateMapper;

    @Override
    public int create(Certificate certificate) {
        try {
            return certificateMapper.create(certificate);
        } catch (PersistenceException exception) {
            throw new DaoLayerException(exception.getMessage());
        }
    }
}
