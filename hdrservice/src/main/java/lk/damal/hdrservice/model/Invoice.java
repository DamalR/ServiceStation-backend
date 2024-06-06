package lk.damal.hdrservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private String invoiceNumber;
    private String customerName;
    private String vehicleNumber;
    private int vehicleMileage;
    private String job;
    private String usedOilBrand;
    private String usedOilViscosity;
    private double usedOilLiters;
    private double usedOilPrice;
    private String serviceDate;
    private double grossAmount;
    private double discountAmount;
    private double netAmount;
    private String paymentMethod;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serviceId")
    private ServiceData serviceData;
}
