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
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    private String fullName;
    private String telephoneNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments = new ArrayList<>();

//    @OneToMany(mappedBy = "customer")
//    private List<Vehicle> vehicles = new ArrayList<>();
//
//    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Service service;

}
