package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.ServiceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceData, Long> {
    public List<ServiceData> findServiceDataByStatus(String status);
}
