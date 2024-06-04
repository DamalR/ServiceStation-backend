package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.CustomerDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Customer;
import lk.damal.hdrservice.repository.CustomerRepository;
import lk.damal.hdrservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseDTO newCustomer(CustomerDTO customerDTO) {

        String fullName = customerDTO.getFullName();
        String telephoneNumber = customerDTO.getTelephoneNumber();

        try {
            Customer filteredCustomer = customerRepository.findByTelephoneNumberAndFullName(telephoneNumber, fullName);

            if (filteredCustomer == null) {
                if (fullName.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Customer name cannot be empty!"
                    );
                } else if (telephoneNumber.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Customer telephone number cannot be empty!"
                    );
                } else {
                    Customer customer = new Customer();

                    customer.setFullName(fullName);
                    customer.setTelephoneNumber(telephoneNumber);

                    Customer save = customerRepository.save(customer);

                    return new ResponseDTO(
                            true,
                            "Customer successfully created!",
                            save.getCustomerId()
                    );
                }
            } else {
                return new ResponseDTO(
                        true,
                        fullName + " is a existing customer!",
                        filteredCustomer.getCustomerId()
                );
            }
        } catch (Exception exception) {
            return new ResponseDTO(
                    false,
                    "Cannot save customer details!",
                    exception
            );
        }

    }
}
