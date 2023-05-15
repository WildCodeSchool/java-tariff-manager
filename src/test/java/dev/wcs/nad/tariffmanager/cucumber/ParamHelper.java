package dev.wcs.nad.tariffmanager.cucumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;

@Component
public class ParamHelper {
    @Autowired CustomerRepository customerRepository;

    Customer getCustomerByName(String name) {
        String firstName = name.split("[ ]")[0];
        String lastName = name.split("[ ]")[1];
        Customer customer = customerRepository.findByFirstnameAndLastname(firstName, lastName).get();
        return customer;
    }
}
