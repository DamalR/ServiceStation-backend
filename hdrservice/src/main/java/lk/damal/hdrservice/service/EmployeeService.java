package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.EmployerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface EmployeeService {
    ResponseDTO newEmployer(EmployerDTO employerDTO);
}
