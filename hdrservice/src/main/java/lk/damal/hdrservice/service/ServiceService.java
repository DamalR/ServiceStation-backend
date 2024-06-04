package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDTO;

public interface ServiceService {
    public ResponseDTO newService(ServiceDTO serviceDTO);
}
