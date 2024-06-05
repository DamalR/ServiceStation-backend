package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.internal.build.AllowNonPortable;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllowNonPortable
@Entity
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employerId;
    private String fullName;
    private String telephoneNumber;
    private String nic;
    private String address;
    private String username;
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId")
    private Role role;

    @OneToOne(mappedBy = "employer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ServiceData serviceData;
}
