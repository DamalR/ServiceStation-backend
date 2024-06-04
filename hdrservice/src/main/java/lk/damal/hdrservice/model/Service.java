package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;
    private String startedTime;
    private String finishedTime;
    private double serviceCharge;
    @OneToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;
    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "employerId")
    private Employer employer;
    @OneToOne
    @JoinColumn(name = "vehicleNumber")
    private Vehicle vehicle;
}
