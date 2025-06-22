package files.vault.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private String rollNo;

    private String firstName;

    private String lastName;

    private String fullName;

    private Department department;

    private String emailAddress;

    private Long phoneNo;

    private String position;
}
