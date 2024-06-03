package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long employerId;
    @Getter
    @Setter
    private String employeeType;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;
}
