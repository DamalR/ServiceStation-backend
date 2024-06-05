package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {
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
    private String grossAmount;
    private double discountAmount;
    private double netAmount;
    private long serviceId;
}
