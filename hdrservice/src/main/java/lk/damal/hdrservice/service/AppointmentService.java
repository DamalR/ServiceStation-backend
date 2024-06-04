package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface AppointmentService {
    public ResponseDTO newAppointment(AppointmentDTO appointmentDTO);
}
