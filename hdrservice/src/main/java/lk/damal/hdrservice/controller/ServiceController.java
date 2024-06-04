package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDTO;
import lk.damal.hdrservice.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/new")
    public ResponseDTO newService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.newService(serviceDTO);
    }
}
