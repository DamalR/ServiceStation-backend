package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.EmployerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface EmployeeService {
    public ResponseDTO newEmployer(EmployerDTO employerDTO);
}
