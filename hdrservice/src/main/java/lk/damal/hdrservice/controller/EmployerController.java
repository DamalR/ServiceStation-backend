package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.EmployerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1.0/employer")
public class EmployerController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/new")
    public ResponseDTO newEmployer(@RequestBody EmployerDTO employerDTO) {
        return employeeService.newEmployer(employerDTO);
    }
}
