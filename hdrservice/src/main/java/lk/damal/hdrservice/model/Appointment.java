package lk.damal.hdrservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long appointmentId;
    @Getter
    @Setter
    private String vehicleNumber;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String time;
    @Getter
    @Setter
    private String customerName;
    @Getter
    @Setter
    private String customerTelephone;
    @Getter
    @Setter
    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Service service;
}
