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
    private String customerTelephone;
    private long serviceId;

    public AppointmentDTO(String vehicleNumber, String date, String time, String customerName, String customerTelephone) {
        this.vehicleNumber = vehicleNumber;
        this.date = date;
        this.time = time;
        this.customerName = customerName;
        this.customerTelephone = customerTelephone;
    }
}
