package files.vault.service;

import commons.exceptions.service.DaoLayerException;
import commons.exceptions.service.ServiceLayerException;
import files.vault.dao.MemberDao;
import files.vault.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired private MemberDao dao;

    @Override
    public int create(Member member) {
        try {
            return dao.create(member);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException(exception.getMessage());
        }
    }

    @Override
    public List<Member> getByNameOrRollNo(String chars) {
        try {
            return dao.getByNameOrRollNo(chars);
        } catch (DaoLayerException exception) {
            throw new ServiceLayerException(exception.getMessage());
        }
    }
}
