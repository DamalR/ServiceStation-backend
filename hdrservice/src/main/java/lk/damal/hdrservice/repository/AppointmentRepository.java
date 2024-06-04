package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("FROM Appointment WHERE vehicleNumber =:vehicleNumber AND date=:date")
    public Appointment findAppointmentByVehicleNumberAndDate(String vehicleNumber, String date);
}
