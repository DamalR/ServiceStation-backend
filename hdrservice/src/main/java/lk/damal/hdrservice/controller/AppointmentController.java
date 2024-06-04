package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/new")
    public ResponseDTO newAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.newAppointment(appointmentDTO);
    }

    @DeleteMapping("/delete")
    public ResponseDTO cancelAppointment(@RequestParam("appointmentId") Long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    @GetMapping("/appointments")
    public ResponseDTO allAppointments() {
        return appointmentService.getAllAppointments();
    }
}
