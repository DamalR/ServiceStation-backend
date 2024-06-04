package lk.damal.hdrservice.dto;

import lk.damal.hdrservice.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private long customerId;
    private String fullName;
    private String telephoneNumber;

}
