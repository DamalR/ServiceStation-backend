package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.RoleDTO;
import lk.damal.hdrservice.model.Role;
import lk.damal.hdrservice.repository.RoleRepository;
import lk.damal.hdrservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseDTO newRole(RoleDTO roleDTO) {
        String employeeRole = roleDTO.getRole();

        if (employeeRole.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Role cannot be null!"
            );
        } else {
            try {
                Role roleByRole = roleRepository.getRoleByRole(employeeRole);

                if (roleByRole == null) {
                    Role role = new Role();

                    role.setRole(employeeRole);

                    roleRepository.save(role);

                    RoleDTO savedRole = new RoleDTO();

                    savedRole.setRoleId(role.getRoleId());
                    savedRole.setRole(role.getRole());

                    return new ResponseDTO(
                            true,
                            "Role added successfully!",
                            savedRole
                    );

                } else {
                    return new ResponseDTO(
                            false,
                            "Role already exists!"
                    );
                }
            } catch (Exception exception) {
                return new ResponseDTO(
                        false,
                        "Role cannot be add!"
                );
            }
        }
    }
}
