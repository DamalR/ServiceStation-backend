package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.CustomerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface CustomerService {
    public ResponseDTO newCustomer(CustomerDTO customerDTO);
}
