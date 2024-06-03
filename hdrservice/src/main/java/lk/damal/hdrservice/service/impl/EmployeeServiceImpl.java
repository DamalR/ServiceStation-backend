package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.EmployerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Employer;
import lk.damal.hdrservice.model.Role;
import lk.damal.hdrservice.repository.EmployerRepository;
import lk.damal.hdrservice.repository.RoleRepository;
import lk.damal.hdrservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseDTO newEmployer(EmployerDTO employerDTO) {
        return null;
    }
}