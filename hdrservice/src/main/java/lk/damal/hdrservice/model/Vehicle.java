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
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vehicleNumber;
    private String manufacture;
    private String vehicleType;
    private String model;
    private String color;
    private String damages;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private List<Appointment> appointments = new ArrayList<>();

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "customerId", nullable = false)
//    private Customer customer;
//
//    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Service service;
}
