package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDataDTO;

public interface ServiceService {
    public ResponseDTO takeService(ServiceDataDTO serviceDataDTO);
}
