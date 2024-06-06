package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.RoleDTO;
import lk.damal.hdrservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/admin")
public class AdminController {

    @Autowired
    private RoleService roleService;;

    @PostMapping("/role/new")
    public ResponseDTO newRole(@RequestBody RoleDTO roleDTO) {
        return roleService.newRole(roleDTO);
    }
}
