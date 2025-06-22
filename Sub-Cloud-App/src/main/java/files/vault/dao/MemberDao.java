package files.vault.dao;

import files.vault.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDao {

    @Autowired
    private MemberMapper memberMapper;

    public String getInfo(Long roll) {
        return memberMapper.getInfo(roll);
    }
}
