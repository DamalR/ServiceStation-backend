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

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
        String vehicleNumber = serviceDataDTO.getVehicleNumber();
        String vehicleType = serviceDataDTO.getVehicleType();
        String startedTime = LocalTime.now(ZoneId.of("GMT+02:30")).toString();
        int currentMileage = serviceDataDTO.getCurrentMileage();

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
            } else if (currentMileage == 0) {
                return new ResponseDTO(
                        false,
                        "Cannot find vehicle mileage!"
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
                vehicleDTO.setManufacture(vehicleManufacture);
                vehicleDTO.setModel(vehicleModel);
                vehicleDTO.setVehicleNumber(vehicleNumber);
                vehicleDTO.setVehicleType(vehicleType);
                vehicleDTO.setCurrentMileage(currentMileage);

                vehicleService.updateAppointmentStatus(vehicleDTO, vehicleId);

//                service transaction
                ServiceData serviceData = new ServiceData();

                serviceData.setStartedTime(startedTime);
                serviceData.setStatus("OnGoing");
                serviceData.setAppointment(appointmentRepositoryById.get());
                serviceData.setCategory(categoryRepositoryById.get());
                serviceData.setEmployer(employerRepositoryById.get());

                serviceRepository.save(serviceData);

                ServiceDataDTO savedServiceData = new ServiceDataDTO();

                savedServiceData.setServiceId(serviceData.getServiceId());
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

    @Override
    public ResponseDTO submitService(ServiceDataDTO serviceDataDTO, Long serviceId) {
        long appointmentId = serviceDataDTO.getAppointmentId();
        long categoryId = serviceDataDTO.getCategoryId();
        long employerId = serviceDataDTO.getEmployerId();
        String startedTime = serviceDataDTO.getStartedTime();

        Optional<ServiceData> serviceById = serviceRepository.findById(serviceId);

        if (serviceById.isPresent()) {
            try {
                if (appointmentId == 0) {
                    return new ResponseDTO(
                            false,
                            "Cannot find appointment!"
                    );
                } else if (categoryId == 0) {
                    return new ResponseDTO(
                            false,
                            "Cannot find category!"
                    );
                } else if (employerId == 0) {
                    return new ResponseDTO(
                            false,
                            "Cannot find employer!"
                    );
                } else if (startedTime.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Cannot find started time!"
                    );
                } else {
                    ServiceData serviceData = serviceById.get();

                    serviceData.setAppointment(appointmentRepository.findById(appointmentId).get());
                    serviceData.setCategory(categoryRepository.findById(categoryId).get());
                    serviceData.setEmployer(employerRepository.findById(employerId).get());
                    serviceData.setStartedTime(startedTime);
                    serviceData.setFinishedTime(LocalTime.now(ZoneId.of("GMT+02:30")).toString());
                    serviceData.setStatus("Done");

                    serviceRepository.save(serviceData);

                    ServiceDataDTO updatedService = new ServiceDataDTO();

                    updatedService.setServiceId(serviceData.getServiceId());
                    updatedService.setFinishedTime(serviceData.getFinishedTime());
                    updatedService.setStartedTime(serviceData.getStartedTime());
                    updatedService.setStatus(serviceData.getStatus());
                    updatedService.setAppointmentId(serviceData.getAppointment().getAppointmentId());
                    updatedService.setCategoryId(serviceData.getCategory().getCategoryId());
                    updatedService.setEmployerId(serviceData.getEmployer().getEmployerId());

                    return new ResponseDTO(
                            true,
                            "Service Records Updated!",
                            updatedService
                    );
                }
            } catch (Exception exception) {
                return new ResponseDTO(
                        false,
                        "Cannot submit service!",
                        exception
                );
            }
        } else {
            return new ResponseDTO(
                    false,
                    "Cannot found any service record!"
            );
        }
    }

    @Override
    public ResponseDTO returnService(ServiceDataDTO serviceDataDTO, Long serviceId) {
        long appointmentId = serviceDataDTO.getAppointmentId();
        long categoryId = serviceDataDTO.getCategoryId();
        long employerId = serviceDataDTO.getEmployerId();
        String startedTime = serviceDataDTO.getStartedTime();

        Optional<ServiceData> serviceById = serviceRepository.findById(serviceId);

        if (serviceById.isPresent()) {
            try {
                if (appointmentId == 0) {
                    return new ResponseDTO(
                            false,
                            "Cannot find appointment!"
                    );
                } else if (categoryId == 0) {
                    return new ResponseDTO(
                            false,
                            "Cannot find category!"
                    );
                } else if (employerId == 0) {
                    return new ResponseDTO(
                            false,
                            "Cannot find employer!"
                    );
                } else if (startedTime.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Cannot find started time!"
                    );
                } else {
                    ServiceData serviceData = serviceById.get();

                    serviceData.setAppointment(appointmentRepository.findById(appointmentId).get());
                    serviceData.setCategory(categoryRepository.findById(categoryId).get());
                    serviceData.setEmployer(employerRepository.findById(employerId).get());
                    serviceData.setStartedTime(startedTime);
                    serviceData.setFinishedTime(LocalTime.now(ZoneId.of("GMT+02:30")).toString());
                    serviceData.setStatus("Returned");

                    serviceRepository.save(serviceData);

                    ServiceDataDTO updatedService = new ServiceDataDTO();

                    updatedService.setServiceId(serviceData.getServiceId());
                    updatedService.setFinishedTime(serviceData.getFinishedTime());
                    updatedService.setStartedTime(serviceData.getStartedTime());
                    updatedService.setStatus(serviceData.getStatus());
                    updatedService.setAppointmentId(serviceData.getAppointment().getAppointmentId());
                    updatedService.setCategoryId(serviceData.getCategory().getCategoryId());
                    updatedService.setEmployerId(serviceData.getEmployer().getEmployerId());

                    return new ResponseDTO(
                            true,
                            "Service vehicle returned!",
                            updatedService
                    );
                }
            } catch (Exception exception) {
                return new ResponseDTO(
                        false,
                        "Cannot return service vehicle!",
                        exception
                );
            }
        } else {
            return new ResponseDTO(
                    false,
                    "Cannot found any service record!"
            );
        }
    }

    @Override
    public ResponseDTO getCompletedService(String status) {
        List<ServiceData> serviceDataByStatus = serviceRepository.findServiceDataByStatus(status);

        if (serviceDataByStatus.isEmpty()) {
            return new ResponseDTO(
                    false,
                    "Finished or Returned service records are not found!"
            );
        } else {
            ArrayList<ServiceDataDTO> serviceDataList = new ArrayList<>();

            for (ServiceData serviceData : serviceDataByStatus) {

                ServiceDataDTO serviceDataDTO = new ServiceDataDTO();

                serviceDataDTO.setServiceId(serviceData.getServiceId());
                serviceDataDTO.setFinishedTime(serviceData.getFinishedTime());
                serviceDataDTO.setStartedTime(serviceData.getStartedTime());
                serviceDataDTO.setStatus(serviceData.getStatus());
                serviceDataDTO.setCategoryId(serviceData.getCategory().getCategoryId());
                serviceDataDTO.setAppointmentId(serviceData.getAppointment().getAppointmentId());
                serviceDataDTO.setEmployerId(serviceData.getEmployer().getEmployerId());
                serviceDataDTO.setVehicleColor(serviceData.getAppointment().getVehicle().getColor());
                serviceDataDTO.setVehicleModel(serviceData.getAppointment().getVehicle().getModel());
                serviceDataDTO.setVehicleManufacture(serviceData.getAppointment().getVehicle().getManufacture());
                serviceDataDTO.setVehicleModel(serviceData.getAppointment().getVehicle().getModel());
                serviceDataDTO.setVehicleType(serviceData.getAppointment().getVehicle().getVehicleType());
                serviceDataDTO.setCustomerId(serviceData.getAppointment().getCustomer().getCustomerId());
                serviceDataDTO.setVehicleId(serviceData.getAppointment().getVehicle().getVehicleId());

                serviceDataList.add(serviceDataDTO);
            }
            return new ResponseDTO(
                    true,
                    "All " + status + " service data are fetched!",
                    serviceDataList
            );
        }
    }
}
