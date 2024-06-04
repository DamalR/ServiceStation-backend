package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private long serviceId;
    private String startedTime;
    private String finishedTime;
    private double additionalCharges;
    private String status;
    private String vehicleColor;
    private String vehicleModel;
    private String vehicleDamages;
    private long categoryId;
    private long appointmentId;
    private long employerId;
}
