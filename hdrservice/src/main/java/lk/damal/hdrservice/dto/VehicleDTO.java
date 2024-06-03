package lk.damal.hdrservice.dto;

import lk.damal.hdrservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    @Getter
    @Setter
    private long vehicleNumber;
    @Getter
    @Setter
    private String manufacture;
    @Getter
    @Setter
    private String vehicleType;
    @Getter
    @Setter
    private String model;
    @Getter
    @Setter
    private String color;
    @Getter
    @Setter
    private String damages;
    @Getter
    @Setter
    private Customer customer;
    @Getter
    @Setter
    private long serviceId;

}
