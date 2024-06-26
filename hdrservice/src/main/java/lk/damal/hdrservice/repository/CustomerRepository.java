package lk.damal.hdrservice.repository;

import lk.damal.hdrservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    public Customer findByTelephoneNumberAndFullName(String telephoneNumber, String fullName);
}
