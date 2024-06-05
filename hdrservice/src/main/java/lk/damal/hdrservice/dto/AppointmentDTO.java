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
    private String date;
    private String time;
    private String status;
    private String customerName;
    private String telephoneNumber;
    private String vehicleNumber;
    private String manufacturer;
    private String vehicleType;
    private Long customerId;
    private Long vehicleId;

    public AppointmentDTO(String date, String time, String status, Long customerId, Long vehicleId) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
    }
}
