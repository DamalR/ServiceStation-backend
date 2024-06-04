package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private long appointmentId;
    private String vehicleNumber;
    private String date;
    private String time;
    private String customerName;
    private String telephoneNumber;
    private Long customerId;
}
