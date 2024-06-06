package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.RoleDTO;

public interface RoleService {
    public ResponseDTO newRole(RoleDTO roleDTO);
}
