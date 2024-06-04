package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.CustomerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.model.Customer;
import lk.damal.hdrservice.repository.AppointmentRepository;
import lk.damal.hdrservice.repository.CustomerRepository;
import lk.damal.hdrservice.service.AppointmentService;
import lk.damal.hdrservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public ResponseDTO newAppointment(AppointmentDTO appointmentDTO) {
        String vehicleNumber = appointmentDTO.getVehicleNumber();
        String date = appointmentDTO.getDate();
        String time = appointmentDTO.getTime();
        String customerName = appointmentDTO.getCustomerName();
        String telephoneNumber = appointmentDTO.getTelephoneNumber();

        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.toString(); //2024-06-04

        Appointment appointmentByVehicleNumberAndDate = appointmentRepository.findAppointmentByVehicleNumberAndDate(vehicleNumber, dateString);
        System.out.println(appointmentByVehicleNumberAndDate);

        if (customerName.equalsIgnoreCase("")){
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
            if (appointmentByVehicleNumberAndDate == null) {
                try {
                    CustomerDTO customerDTO = new CustomerDTO();

                    customerDTO.setFullName(customerName);
                    customerDTO.setTelephoneNumber(telephoneNumber);

                    ResponseDTO responseDTO = customerService.newCustomer(customerDTO);
                    Object data = responseDTO.getData();

                    Optional<Customer> repositoryById = customerRepository.findById(Long.parseLong(data.toString()));
                    Customer customer = repositoryById.get();

                    Appointment appointment = new Appointment();

                    appointment.setVehicleNumber(vehicleNumber);
                    appointment.setDate(date);
                    appointment.setTime(time);
                    appointment.setCustomer(customer);

                    appointmentRepository.save(appointment);

                    AppointmentDTO savedAppointment = new AppointmentDTO();

                    savedAppointment.setAppointmentId(appointment.getAppointmentId());
                    savedAppointment.setVehicleNumber(appointment.getVehicleNumber());
                    savedAppointment.setCustomerName(appointment.getCustomer().getFullName());
                    savedAppointment.setTelephoneNumber(appointment.getCustomer().getTelephoneNumber());
                    savedAppointment.setCustomerId(appointment.getCustomer().getCustomerId());
                    savedAppointment.setDate(appointment.getDate());
                    savedAppointment.setTime(appointment.getTime());

                    return new ResponseDTO(
                            true,
                            vehicleNumber + " appointment has placed!",
                            savedAppointment
                    );

                } catch (Exception exception) {
                    return new ResponseDTO(
                            false,
                            "Cannot make this appointment",
                            exception
                    );
                }
            } else {
                return new ResponseDTO(
                        false,
                        vehicleNumber + " Vehicle already has an appointment!"
                );
            }
        }
    }
}
