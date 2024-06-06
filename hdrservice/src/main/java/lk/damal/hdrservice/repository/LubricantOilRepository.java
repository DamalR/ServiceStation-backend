package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.dto.LubricantOilDTO;
import lk.damal.hdrservice.model.LubricantOil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LubricantOilRepository extends JpaRepository<LubricantOil, Long> {
    public LubricantOil getLubricantOilByBrandAndViscosityAndFuelTypeAndLitersAndPrice(String brand, String velocity, String fuelType, double liters, double price);
}
