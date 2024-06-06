package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDataDTO;

public interface ServiceService {
    public ResponseDTO takeService(ServiceDataDTO serviceDataDTO);
    public ResponseDTO submitService(ServiceDataDTO serviceDataDTO, Long serviceId);
    public ResponseDTO returnService(ServiceDataDTO serviceDataDTO, Long serviceId);
    public ResponseDTO getCompletedService(String status);
}
