package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
}
