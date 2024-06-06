package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.LubricantOilDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.LubricantOil;
import lk.damal.hdrservice.repository.LubricantOilRepository;
import lk.damal.hdrservice.service.LubricantOilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LubricantOilServiceImpl implements LubricantOilService {

    @Autowired
    private LubricantOilRepository lubricantOilRepository;

    @Override
    public ResponseDTO newLubricantOil(LubricantOilDTO lubricantOilDTO) {
        String brand = lubricantOilDTO.getBrand();
        String viscosity = lubricantOilDTO.getViscosity();
        String fuelType = lubricantOilDTO.getFuelType();
        double liters = lubricantOilDTO.getLiters();
        double price = lubricantOilDTO.getPrice();

        if (brand.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Brand cannot be empty!"
            );
        } else if (viscosity.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "Viscosity cannot be empty!"
            );
        } else if (fuelType.equalsIgnoreCase("")) {
            return new ResponseDTO(
                    false,
                    "FuelType cannot be empty!"
            );
        } else if (liters <= 0) {
            return new ResponseDTO(
                    false,
                    "Liters cannot be less than 0!"
            );
        } else if (price <= 0) {
            return new ResponseDTO(
                    false,
                    "Price cannot be less than 0!"
            );
        } else {
            try {
                LubricantOil lubricantOilByData = lubricantOilRepository.getLubricantOilByBrandAndViscosityAndFuelTypeAndLitersAndPrice(brand, viscosity, fuelType, liters, price);

                if (lubricantOilByData == null) {

                    LubricantOil lubricantOil = new LubricantOil();

                    lubricantOil.setBrand(brand);
                    lubricantOil.setViscosity(viscosity);
                    lubricantOil.setFuelType(fuelType);
                    lubricantOil.setLiters(liters);
                    lubricantOil.setPrice(price);

                    lubricantOilRepository.save(lubricantOil);

                    LubricantOilDTO savedLubricantOil = new LubricantOilDTO();

                    savedLubricantOil.setOilId(lubricantOil.getOilId());
                    savedLubricantOil.setBrand(lubricantOil.getBrand());
                    savedLubricantOil.setViscosity(viscosity);
                    savedLubricantOil.setFuelType(fuelType);
                    savedLubricantOil.setLiters(liters);
                    savedLubricantOil.setPrice(price);

                    return new ResponseDTO(
                            true,
                            "Lubricant Oil Saved!",
                            savedLubricantOil
                    );
                } else {
                    return new ResponseDTO(
                            false,
                            "This oil already exist!"
                    );
                }
            } catch (Exception exception) {;
                return new ResponseDTO(
                        false,
                        "This lubricant oil can not add!",
                        exception
                );
            }
        }
    }
}
