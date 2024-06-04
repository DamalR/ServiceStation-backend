package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDTO;
import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.repository.AppointmentRepository;
import lk.damal.hdrservice.repository.ServiceRepository;
import lk.damal.hdrservice.service.AppointmentService;
import lk.damal.hdrservice.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public ResponseDTO newService(ServiceDTO serviceDTO) {
        long employerId = serviceDTO.getEmployerId();
        long categoryId = serviceDTO.getCategoryId();
        long appointmentId = serviceDTO.getAppointmentId();
        String vehicleModel = serviceDTO.getVehicleModel();
        String vehicleColor = serviceDTO.getVehicleColor();
        String vehicleDamages = serviceDTO.getVehicleDamages();
        String startedTime = serviceDTO.getStartedTime();
        String finishedTime = serviceDTO.getFinishedTime();
        double additionalCharges = serviceDTO.getAdditionalCharges();
        String status = serviceDTO.getStatus();

        Optional<Appointment> repositoryById = appointmentRepository.findById(appointmentId);

        return null;
    }
}
