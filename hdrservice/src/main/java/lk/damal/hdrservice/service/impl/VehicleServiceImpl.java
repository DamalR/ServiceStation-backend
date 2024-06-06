package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.VehicleDTO;
import lk.damal.hdrservice.model.Vehicle;
import lk.damal.hdrservice.repository.VehicleRepository;
import lk.damal.hdrservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseDTO newVehicle(VehicleDTO vehicleDTO) {

        System.out.println("Vehicle Data");

        String vehicleNumber = vehicleDTO.getVehicleNumber();
        String manufacture = vehicleDTO.getManufacture();
        String vehicleType = vehicleDTO.getVehicleType();
        System.out.println(vehicleNumber);
        System.out.println(manufacture);
        System.out.println(vehicleType);

        try {
            if (vehicleNumber.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Vehicle Number cannot be null!"
                );
            } else if (manufacture.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Manufacture cannot be null!"
                );
            } else if (vehicleType.equalsIgnoreCase("")) {
                return new ResponseDTO(
                        false,
                        "Vehicle type cannot be null!"
                );
            } else {
                Vehicle vehicle = new Vehicle();

                vehicle.setVehicleNumber(vehicleNumber);
                vehicle.setManufacture(manufacture);
                vehicle.setVehicleType(vehicleType);

                Vehicle save = vehicleRepository.save(vehicle);

                return new ResponseDTO(
                        true,
                        "New Vehicle Placed Successfully!",
                        save.getVehicleId()
                );
            }
        } catch (Exception exception) {
            return new ResponseDTO(
                    false,
                    "Cannot place any vehicle!",
                    exception
            );
        }
    }

    @Override
    public ResponseDTO updateAppointmentStatus(VehicleDTO vehicleDTO, Long vehicleId) {
        String color = vehicleDTO.getColor();
        String manufacture = vehicleDTO.getManufacture();
        String model = vehicleDTO.getModel();
        String vehicleNumber = vehicleDTO.getVehicleNumber();
        String vehicleType = vehicleDTO.getVehicleType();
        int currentMileage = vehicleDTO.getCurrentMileage();

        Optional<Vehicle> vehicleById = vehicleRepository.findById(vehicleId);

        try {
            if (vehicleById.isPresent()) {
                try {
                    if (color.equalsIgnoreCase("")) {
                        return new ResponseDTO(
                                false,
                                "Vehicle color cannot be null!"
                        );
                    } else if (manufacture.equalsIgnoreCase("")) {
                        return new ResponseDTO(
                                false,
                                "Manufacture cannot be null!"
                        );
                    } else if (model.equalsIgnoreCase("")) {
                        return new ResponseDTO(
                                false,
                                "Model cannot be null!"
                        );
                    } else if (vehicleNumber.equalsIgnoreCase("")) {
                        return new ResponseDTO(
                                false,
                                "Vehicle Number cannot be null!"
                        );
                    } else if (vehicleType.equalsIgnoreCase("")) {
                        return new ResponseDTO(
                                false,
                                "Vehicle type cannot be null!"
                        );
                    } else if (currentMileage == 0) {
                        return new ResponseDTO(
                                false,
                                "Vehicle mileage cannot be null!"
                        );
                    } else {

                        Vehicle vehicle = vehicleById.get();

                        vehicle.setColor(color);
                        vehicle.setManufacture(manufacture);
                        vehicle.setModel(model);
                        vehicle.setVehicleNumber(vehicleNumber);
                        vehicle.setVehicleType(vehicleType);
                        vehicle.setCurrentMileage(currentMileage);

                        vehicleRepository.save(vehicle);

                        VehicleDTO updatedVehicle = new VehicleDTO();

                        updatedVehicle.setColor(vehicle.getColor());
                        updatedVehicle.setManufacture(vehicle.getManufacture());
                        updatedVehicle.setModel(vehicle.getModel());
                        updatedVehicle.setVehicleNumber(vehicle.getModel());
                        updatedVehicle.setVehicleType(vehicle.getVehicleType());
                        updatedVehicle.setCurrentMileage(vehicle.getCurrentMileage());

                        return new ResponseDTO(
                                true,
                                "Vehicle detail has updated successfully!",
                                updatedVehicle
                        );
                    }
                } catch (Exception exception) {
                    return new ResponseDTO(
                            false,
                            "Something went wrong, vehicle can not update!",
                            exception
                    );
                }
            } else {
                return new ResponseDTO(
                        false,
                        "Vehicle not found to update!"
                );
            }
        } catch (Exception exception) {
            return new ResponseDTO(
                    false,
                    "Something went wrong, please try again",
                    exception
            );
        }
    }
}
