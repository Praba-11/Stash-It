package heroic.vault.service;

import heroic.vault.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired private DAO dao;

    public String getInfo() {
        return dao.getInfo(1L);
    }
}
