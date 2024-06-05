package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.ServiceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceData, Long> {
}
