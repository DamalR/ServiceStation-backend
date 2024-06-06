package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDataDTO;
import lk.damal.hdrservice.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/new")
    public ResponseDTO takeService(@RequestBody ServiceDataDTO serviceDataDTO) {
        return serviceService.takeService(serviceDataDTO);
    }

    @PutMapping("/submit")
    public ResponseDTO submitService(@RequestBody ServiceDataDTO serviceDataDTO, @RequestParam("serviceId") Long serviceId) {
        return serviceService.submitService(serviceDataDTO, serviceId);
    }

    @PutMapping("/return")
    public ResponseDTO returnService(@RequestBody ServiceDataDTO serviceDataDTO, @RequestParam("serviceId") Long serviceId) {
        return serviceService.returnService(serviceDataDTO, serviceId);
    }

    @GetMapping("/completed")
    public ResponseDTO getCompletedService(@RequestParam("status") String status) {
        return serviceService.getCompletedService(status);
    }
}
