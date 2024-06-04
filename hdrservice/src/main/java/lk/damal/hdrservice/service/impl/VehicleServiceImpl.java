package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.VehicleDTO;
import lk.damal.hdrservice.model.Vehicle;
import lk.damal.hdrservice.repository.VehicleRepository;
import lk.damal.hdrservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseDTO newVehicle(VehicleDTO vehicleDTO) {

        String vehicleNumber = vehicleDTO.getVehicleNumber();
        String manufacture = vehicleDTO.getManufacture();
        String vehicleType = vehicleDTO.getVehicleType();

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
}
