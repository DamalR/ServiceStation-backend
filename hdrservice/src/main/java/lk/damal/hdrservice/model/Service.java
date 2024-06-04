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
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long serviceId;
    private String startedTime;
    private String finishedTime;
    private double additionalCharges;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "employerId")
    private Employer employer;
}
