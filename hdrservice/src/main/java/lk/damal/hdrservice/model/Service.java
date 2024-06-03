package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long serviceId;
    @Getter
    @Setter
    private String startedTime;
    @Getter
    @Setter
    private String finishedTime;
    @Getter
    @Setter
    private double serviceCharge;
    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;
    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "employerId")
    private Employer employer;
    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "vehicleNumber")
    private Vehicle vehicle;
}
