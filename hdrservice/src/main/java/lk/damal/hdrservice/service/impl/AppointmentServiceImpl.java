package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.repository.AppointmentRepository;
import lk.damal.hdrservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public ResponseDTO newAppointment(AppointmentDTO appointmentDTO) {
        String customerName = appointmentDTO.getCustomerName();
        String vehicleNumber = appointmentDTO.getVehicleNumber();
        String customerTelephone = appointmentDTO.getCustomerTelephone();
        String date = appointmentDTO.getDate();
        String time = appointmentDTO.getTime();

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
        } else if (customerTelephone.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Telephone number cannot be empty!"
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
                    Appointment appointment = new Appointment();

                    appointment.setCustomerName(customerName);
                    appointment.setVehicleNumber(vehicleNumber);
                    appointment.setCustomerTelephone(customerTelephone);
                    appointment.setDate(date);
                    appointment.setTime(time);

                    appointmentRepository.save(appointment);

                    AppointmentDTO savedAppointment = new AppointmentDTO();

                    savedAppointment.setAppointmentId(appointment.getAppointmentId());
                    savedAppointment.setCustomerName(appointment.getCustomerName());
                    savedAppointment.setVehicleNumber(appointment.getVehicleNumber());
                    savedAppointment.setCustomerTelephone(appointment.getCustomerTelephone());
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
