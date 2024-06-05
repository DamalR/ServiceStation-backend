package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public List<Appointment> findAppointmentsByStatus(String status);
}
