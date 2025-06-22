package files.vault.service;

import files.vault.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired private MemberDao memberDao;

    public String getInfo() {
        return memberDao.getInfo(1L);
    }
}
