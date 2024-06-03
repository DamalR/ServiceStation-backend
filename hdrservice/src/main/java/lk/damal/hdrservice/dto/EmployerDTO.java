package lk.damal.hdrservice.dto;

import lk.damal.hdrservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class EmployerDTO {
    @Getter
    @Setter
    private long employerId;
    @Getter
    @Setter
    private String fullName;
    @Getter
    @Setter
    private String telephoneNumber;
    @Getter
    @Setter
    private String nic;
    @Getter
    @Setter
    private String address;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private Long roleId;
}
