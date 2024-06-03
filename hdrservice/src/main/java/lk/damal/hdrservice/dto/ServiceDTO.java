package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
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
    @Getter
    @Setter
    private long appointmentId;
    @Getter
    @Setter
    private long customerId;
    @Getter
    @Setter
    private long employerId;
    @Getter
    @Setter
    private long vehicleNumber;
}
