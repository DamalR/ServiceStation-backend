package lk.damal.hdrservice.dto;

import lk.damal.hdrservice.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private long customerId;
    private String fullName;
    private String phone;
    private List<Vehicle> vehicles = new ArrayList<>();
    private long serviceId;

}
