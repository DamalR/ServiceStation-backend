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
    private double serviceCharge;
    private long appointmentId;
    private long customerId;
    private long employerId;
    private long vehicleNumber;
}
