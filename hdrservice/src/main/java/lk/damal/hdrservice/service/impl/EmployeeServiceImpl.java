package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.EmployerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Employer;
import lk.damal.hdrservice.model.Role;
import lk.damal.hdrservice.repository.EmployerRepository;
import lk.damal.hdrservice.repository.RoleRepository;
import lk.damal.hdrservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
        Long roleId = employerDTO.getRoleId();
        String fullName = employerDTO.getFullName();
        String telephoneNumber = employerDTO.getTelephoneNumber();
        String nic = employerDTO.getNic();
        String address = employerDTO.getAddress();
        String username = employerDTO.getUsername();
        String password = employerDTO.getPassword();

        Employer employerByNic = employerRepository.getEmployerByNic(nic);
        Employer employerByUserName = employerRepository.getEmployerByUsername(username);
        Optional<Role> roleById = roleRepository.findById(roleId);

        if (roleById.isPresent()) {

            Role role = roleById.get();

            if (telephoneNumber.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Telephone Number cannot be empty!"
                );
            } else if (fullName.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Full Name cannot be empty!"
                );
            } else if (nic.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "NIC cannot be empty!"
                );
            } else if (address.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Address cannot be empty!"
                );
            } else if (username.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Username cannot be empty!"
                );
            } else if (password.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Password cannot be empty!"
                );
            } else {
                try {
                    if (employerByNic != null) {
                        return new ResponseDTO(
                                false,
                                "Employee already registered with " + nic + " number!"
                        );
                    } else if (employerByUserName != null) {
                        return new ResponseDTO(
                                false,
                                "Username already in used!"
                        );
                    } else {


                        Employer employer = new Employer();

                        employer.setRole(role);
                        employer.setFullName(employerDTO.getFullName());
                        employer.setTelephoneNumber(employerDTO.getTelephoneNumber());
                        employer.setNic(employerDTO.getNic());
                        employer.setAddress(employerDTO.getAddress());
                        employer.setUsername(employerDTO.getUsername());
                        employer.setPassword(employerDTO.getPassword());

                        employerRepository.save(employer);

                        EmployerDTO savedEmployer = new EmployerDTO();

                        savedEmployer.setEmployerId(employer.getEmployerId());
                        savedEmployer.setRoleId(employer.getRole().getRoleId());
                        savedEmployer.setFullName(employer.getFullName());
                        savedEmployer.setTelephoneNumber(employer.getTelephoneNumber());
                        savedEmployer.setNic(employer.getNic());
                        savedEmployer.setAddress(employer.getAddress());
                        savedEmployer.setUsername(employer.getUsername());
                        savedEmployer.setPassword(employer.getPassword());

                        return new ResponseDTO(
                                true,
                                fullName + " has added successfully!",
                                savedEmployer
                        );

                    }
                } catch (Exception e) {
                    return new ResponseDTO(
                            false,
                            "Employer Adding Failure!"
                    );
                }
            }
        } else {
            return new ResponseDTO(
                    false,
                    "Role cannot found!"
            );
        }
    }
}