package lk.damal.hdrservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LubricantOilDTO {
    private Long oilId;
    private String brand;
    private String viscosity;
    private double liters;
    private String fuelType;
    private double price;
}
