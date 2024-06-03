package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long vehicleNumber;
    @Getter
    @Setter
    private String manufacture;
    @Getter
    @Setter
    private String vehicleType;
    @Getter
    @Setter
    private String model;
    @Getter
    @Setter
    private String color;
    @Getter
    @Setter
    private String damages;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
}
