package lk.damal.hdrservice.dto;

import lk.damal.hdrservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private Long vehicleId;
    private String vehicleNumber;
    private String manufacture;
    private String vehicleType;
    private String model;
    private String color;
    private String damages;
}
