package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDataDTO {
    private long serviceId;
    private String startedTime;
    private String finishedTime;
    private String status;
    private String vehicleColor;
    private String vehicleModel;
    private String vehicleManufacture;
    private String vehicleNumber;
    private String vehicleType;
    private long categoryId;
    private long appointmentId;
    private long employerId;
    private long customerId;
    private long vehicleId;
}
