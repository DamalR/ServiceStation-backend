package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
    public Employer getEmployerByNic(String nic);
    public Employer getEmployerByUsername(String username);
}
