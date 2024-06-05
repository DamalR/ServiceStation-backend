package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.ServiceDataDTO;
import lk.damal.hdrservice.dto.VehicleDTO;
import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.model.Category;
import lk.damal.hdrservice.model.Employer;
import lk.damal.hdrservice.model.ServiceData;
import lk.damal.hdrservice.repository.AppointmentRepository;
import lk.damal.hdrservice.repository.CategoryRepository;
import lk.damal.hdrservice.repository.EmployerRepository;
import lk.damal.hdrservice.repository.ServiceRepository;
import lk.damal.hdrservice.service.AppointmentService;
import lk.damal.hdrservice.service.ServiceService;
import lk.damal.hdrservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private VehicleService vehicleService;

    @Override
    @Transactional
    public ResponseDTO takeService(ServiceDataDTO serviceDataDTO) {
        long employerId = serviceDataDTO.getEmployerId();
        long categoryId = serviceDataDTO.getCategoryId();
        long appointmentId = serviceDataDTO.getAppointmentId();
        long customerId = serviceDataDTO.getCustomerId();
        long vehicleId = serviceDataDTO.getVehicleId();
        String vehicleManufacture = serviceDataDTO.getVehicleManufacture();
        String vehicleModel = serviceDataDTO.getVehicleModel();
        String vehicleColor = serviceDataDTO.getVehicleColor();
        String vehicleDamages = serviceDataDTO.getVehicleDamages();
        String vehicleNumber = serviceDataDTO.getVehicleNumber();
        String vehicleType = serviceDataDTO.getVehicleType();
        String startedTime = serviceDataDTO.getStartedTime();
        double additionalCharges = serviceDataDTO.getAdditionalCharges();
        String status = serviceDataDTO.getStatus();

        Optional<Appointment> appointmentRepositoryById = appointmentRepository.findById(appointmentId);
        Optional<Category> categoryRepositoryById = categoryRepository.findById(categoryId);
        Optional<Employer> employerRepositoryById = employerRepository.findById(employerId);

        if (appointmentRepositoryById.isPresent()
                && categoryRepositoryById.isPresent()
                && employerRepositoryById.isPresent()) {
            if (employerId == 0) {
                return new ResponseDTO(
                        false,
                        "Cannot find employer!"
                );
            } else if (categoryId == 0) {
                return new ResponseDTO(
                        false,
                        "Cannot find service category!"
                );
            } else if (customerId == 0) {
                return new ResponseDTO(
                        false,
                        "Cannot find customer!"
                );
            } else if (vehicleId == 0) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle!"
                );
            }else if (appointmentId == 0) {
                return new ResponseDTO(
                        false,
                        "Cannot find appointment!"
                );
            } else if (vehicleManufacture.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle model!"
                );
            } else if (vehicleModel.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle manufacture!"
                );
            } else if (vehicleNumber.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle number!"
                );
            } else if (vehicleType.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle type!"
                );
            } else if (vehicleColor.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle color!"
                );
            } else if (startedTime.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle started time!"
                );
            } else if (status.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Cannot find appointment status!"
                );
            } else {

//                update appointment status
                AppointmentDTO appointmentDTO = new AppointmentDTO();

                appointmentDTO.setDate(appointmentRepositoryById.get().getDate());
                appointmentDTO.setTime(appointmentRepositoryById.get().getTime());
                appointmentDTO.setStatus("OnGoing");
                appointmentDTO.setCustomerId(customerId);
                appointmentDTO.setVehicleId(vehicleId);

                appointmentService.updateAppointmentStatus(appointmentDTO, appointmentId);

//                update vehicle details
                VehicleDTO vehicleDTO = new VehicleDTO();

                vehicleDTO.setColor(vehicleColor);
                vehicleDTO.setDamages(vehicleDamages);
                vehicleDTO.setManufacture(vehicleManufacture);
                vehicleDTO.setModel(vehicleModel);
                vehicleDTO.setVehicleNumber(vehicleNumber);
                vehicleDTO.setVehicleType(vehicleType);

                ResponseDTO vehicleResponseDTO = vehicleService.updateAppointmentStatus(vehicleDTO, vehicleId);
                Object vehicleData = vehicleResponseDTO.getData();

//                service transaction
                ServiceData serviceData = new ServiceData();

                serviceData.setAdditionalCharges(additionalCharges);
                serviceData.setStartedTime(startedTime);
                serviceData.setStatus("OnGoing");
                serviceData.setAppointment(appointmentRepositoryById.get());
                serviceData.setCategory(categoryRepositoryById.get());
                serviceData.setEmployer(employerRepositoryById.get());

                serviceRepository.save(serviceData);

                ServiceDataDTO savedServiceData = new ServiceDataDTO();

                savedServiceData.setServiceId(serviceData.getServiceId());
                savedServiceData.setAdditionalCharges(serviceData.getAdditionalCharges());
                savedServiceData.setFinishedTime(serviceData.getFinishedTime());
                savedServiceData.setStartedTime(serviceData.getStartedTime());
                savedServiceData.setStatus(serviceData.getStatus());
                savedServiceData.setAppointmentId(serviceData.getAppointment().getAppointmentId());
                savedServiceData.setCategoryId(serviceData.getCategory().getCategoryId());
                savedServiceData.setEmployerId(serviceData.getEmployer().getEmployerId());

                return new ResponseDTO(
                        true,
                        "Service Records Updated Successfully",
                        savedServiceData
                );
            }
        } else {
            return new ResponseDTO(
                    false,
                    "No any selected appointment!"
            );
        }
    }
}
