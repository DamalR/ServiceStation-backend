package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface AppointmentService {
    public ResponseDTO newAppointment(AppointmentDTO appointmentDTO);
    public ResponseDTO cancelAppointment(Long appointmentId);
    public ResponseDTO getAllAppointments();
    public ResponseDTO updateAppointmentStatus(AppointmentDTO appointmentDTO, Long appointmentId);
}
