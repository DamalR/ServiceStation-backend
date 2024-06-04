package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.dto.VehicleDTO;

public interface VehicleService {
    public ResponseDTO newVehicle(VehicleDTO vehicleDTO);
}
