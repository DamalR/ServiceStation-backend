package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceServiceRepository extends JpaRepository<Invoice, Long> {
}
