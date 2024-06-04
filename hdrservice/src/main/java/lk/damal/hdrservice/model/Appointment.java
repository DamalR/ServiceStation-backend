package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appointmentId;
    private String vehicleNumber;
    private String date;
    private String time;
    private String customerName;
    private String customerTelephone;
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Service service;
}
