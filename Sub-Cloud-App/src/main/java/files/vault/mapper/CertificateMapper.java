package files.vault.mapper;

import files.vault.domain.entity.Certificate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface CertificateMapper {

    @Insert("INSERT INTO certificate (title, type, issued_on, expired_on, organization, description) " +
            "VALUES (#{title}, #{type}, #{issuedOn}, #{expiredOn}, #{organization}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int create(Certificate certificate);

}
