package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDTO {
    private long employerId;
    private String fullName;
    private String telephoneNumber;
    private String nic;
    private String address;
    private String username;
    private String password;
    private Long roleId;
}
