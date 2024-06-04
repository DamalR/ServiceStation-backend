package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.CustomerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.VehicleDTO;
import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.model.Customer;
import lk.damal.hdrservice.model.Vehicle;
import lk.damal.hdrservice.repository.AppointmentRepository;
import lk.damal.hdrservice.repository.CustomerRepository;
import lk.damal.hdrservice.repository.VehicleRepository;
import lk.damal.hdrservice.service.AppointmentService;
import lk.damal.hdrservice.service.CustomerService;
import lk.damal.hdrservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Override
    @Transactional
    public ResponseDTO newAppointment(AppointmentDTO appointmentDTO) {

        String date = appointmentDTO.getDate();
        String time = appointmentDTO.getTime();
        String customerName = appointmentDTO.getCustomerName();
        String telephoneNumber = appointmentDTO.getTelephoneNumber();
        String vehicleNumber = appointmentDTO.getVehicleNumber();
        String manufacturer = appointmentDTO.getManufacturer();
        String vehicleType = appointmentDTO.getVehicleType();

        if (customerName.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Customer name cannot be empty!"
            );
        } else if (vehicleNumber.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Vehicle number cannot be empty!"
            );
        } else if (vehicleNumber.length() < 6 || vehicleNumber.length() > 8) {
            return new ResponseDTO(
                    false,
                    "Invalid vehicle number"
            );
        } else if (telephoneNumber.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Telephone number cannot be empty!"
            );
        } else if (telephoneNumber.length() != 10) {
            return new ResponseDTO(
                    false,
                    "Invalid telephone number"
            );

        } else if (date.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Date cannot be empty!"
            );
        } else if (time.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Time cannot be empty!"
            );
        } else {
            try {
//                    customer transaction.
                CustomerDTO customerDTO = new CustomerDTO();

                customerDTO.setFullName(customerName);
                customerDTO.setTelephoneNumber(telephoneNumber);

                ResponseDTO customerResponseDTO = customerService.newCustomer(customerDTO);
                Object customerData = customerResponseDTO.getData();

                Optional<Customer> customerRepositoryById = customerRepository.findById(Long.parseLong(customerData.toString()));
                Customer customer = customerRepositoryById.get();

//                    vehicle transaction
                VehicleDTO vehicleDTO = new VehicleDTO();

                vehicleDTO.setVehicleNumber(vehicleNumber);
                vehicleDTO.setManufacture(manufacturer);
                vehicleDTO.setVehicleType(vehicleType);

                ResponseDTO vehicleResponseDTO = vehicleService.newVehicle(vehicleDTO);
                Object vehicleData = vehicleResponseDTO.getData();

                Optional<Vehicle> vehicleRepositoryById = vehicleRepository.findById(Long.parseLong(vehicleData.toString()));
                Vehicle vehicle = vehicleRepositoryById.get();

//                    appointment transaction
                Appointment appointment = new Appointment();

                appointment.setDate(date);
                appointment.setTime(time);
                appointment.setVehicle(vehicle);
                appointment.setCustomer(customer);

                appointmentRepository.save(appointment);

                AppointmentDTO savedAppointment = new AppointmentDTO();

                savedAppointment.setAppointmentId(appointment.getAppointmentId());
                savedAppointment.setDate(appointment.getDate());
                savedAppointment.setTime(appointment.getTime());
                savedAppointment.setCustomerName(appointment.getCustomer().getFullName());
                savedAppointment.setTelephoneNumber(appointment.getCustomer().getTelephoneNumber());
                savedAppointment.setVehicleNumber(appointment.getVehicle().getVehicleNumber());
                savedAppointment.setManufacturer(appointment.getVehicle().getManufacture());
                savedAppointment.setVehicleType(appointment.getVehicle().getVehicleType());
                savedAppointment.setCustomerId(appointment.getCustomer().getCustomerId());
                savedAppointment.setVehicleId(appointment.getVehicle().getVehicleId());

                return new ResponseDTO(
                        true,
                        vehicleNumber + " appointment has placed!",
                        savedAppointment
                );
            } catch (Exception exception) {
                throw exception;
//                return new ResponseDTO(
//                        false,
//                        "Cannot make this appointment",
//                        exception
//                );
            }
        }
    }

    @Override
    public ResponseDTO cancelAppointment(Long appointmentId) {
        try {
            Optional<Appointment> repositoryById = appointmentRepository.findById(appointmentId);

            if (repositoryById.isPresent()) {
                try {
                    Appointment appointment = repositoryById.get();

                    AppointmentDTO appointmentDTO = new AppointmentDTO();

                    appointmentDTO.setAppointmentId(appointment.getAppointmentId());
                    appointmentDTO.setDate(appointment.getDate());
                    appointmentDTO.setTime(appointment.getTime());
                    appointmentDTO.setCustomerName(appointment.getCustomer().getFullName());
                    appointmentDTO.setTelephoneNumber(appointment.getCustomer().getTelephoneNumber());
                    appointmentDTO.setVehicleNumber(appointment.getVehicle().getVehicleNumber());
                    appointmentDTO.setManufacturer(appointment.getVehicle().getManufacture());
                    appointmentDTO.setVehicleType(appointment.getVehicle().getVehicleType());
                    appointmentDTO.setCustomerId(appointment.getCustomer().getCustomerId());
                    appointmentDTO.setVehicleId(appointment.getVehicle().getVehicleId());

                    appointmentRepository.delete(appointment);

                    return new ResponseDTO(
                            true,
                            "Appointment has deleted!",
                            appointmentDTO
                    );

                } catch (Exception exception) {
                    return new ResponseDTO(
                            false,
                            "Can not cansel this appointment!"
                    );
                }
            } else {
                return new ResponseDTO(
                        false,
                        "Can not find the appointment!"
                );
            }

        } catch (Exception exception) {
            return new ResponseDTO(
                    false,
                    "Something wens wrong, please try again",
                    exception
            );
        }
    }
}
