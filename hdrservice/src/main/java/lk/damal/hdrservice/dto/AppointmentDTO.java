package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor@AllArgsConstructor
public class AppointmentDTO {
    @Getter
    @Setter
    private long appointmentId;
    @Getter
    @Setter
    private String vehicleNumber;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String time;
    @Getter
    @Setter
    private String customerName;
    @Getter
    @Setter
    private String customerTelephone;
    @Getter
    @Setter
    private long serviceId;

    public AppointmentDTO(String vehicleNumber, String date, String time, String customerName, String customerTelephone) {
        this.vehicleNumber = vehicleNumber;
        this.date = date;
        this.time = time;
        this.customerName = customerName;
        this.customerTelephone = customerTelephone;
    }
}
