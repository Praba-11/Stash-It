package files.vault.service;

import commons.exceptions.service.DaoLayerException;
import commons.exceptions.service.ServiceLayerException;
import files.vault.dao.CertificateDao;
import files.vault.domain.entity.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateDao certificateDao;

    @Override
    public int create(Certificate certificate) {
        try {
            return certificateDao.create(certificate);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException(exception.getMessage());
        }
    }
}
