package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vehicleNumber;
    private String manufacture;
    private String vehicleType;
    private String model;
    private String color;
    private String damages;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    @OneToOne(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Service service;
}
