package dev.wcs.nad.tariffmanager.cucumber;

import static org.junit.Assert.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import io.cucumber.java.fr.*;

public class CustomerSteps {

    @Autowired
    CustomerRepository customerRepository;

    @Etantdonné("le(s) client(s) suivant(s):")
    public void givenCustomers(List<List<String>> data) {
        System.out.println("customersData: " + data);

        for (List<String> customerData : data) {
            var customer = new Customer();
            customer.setFirstname(customerData.get(0));
            customer.setLastname(customerData.get(1));
            customerRepository.saveAndFlush(customer);
        }
    }
}
