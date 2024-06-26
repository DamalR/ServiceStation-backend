package lk.damal.hdrservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<ServiceData> serviceData = new ArrayList<>();

}
