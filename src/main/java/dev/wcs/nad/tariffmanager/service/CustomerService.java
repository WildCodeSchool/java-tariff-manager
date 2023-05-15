package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.*;
import dev.wcs.nad.tariffmanager.persistence.repository.AddressRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.ContactRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    // Challenge: Make legalAge configurable
    private final int legalAge;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, ContactRepository contactRepository, @Value("${legal.age}") int legalAge) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.legalAge = legalAge;
    }

    public Iterable<Customer> readAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customerEntity) {
        return customerRepository.save(customerEntity);
    }
    
    public Customer assignAddress(Long customerId, Address mapAddressDto) {
        Address addressEntity = addressRepository.saveAndFlush(mapAddressDto);
        Customer customer = customerRepository.findById(customerId).get();
        Contact contact = customer.getContact();
        if (contact == null) {
            contact = new Contact();
        }
        contact.getAddresses().add(addressEntity);
        contact = contactRepository.saveAndFlush(contact);
        customer.setContact(contact);
        customer = customerRepository.saveAndFlush(customer);
        
        addressEntity.setContact(contact);
        addressRepository.saveAndFlush(addressEntity);
        return customer;
    }

    //After retrieving all customers from the database, create a Stream and filter for all customers aged 18 or older.
    //In-Memory-Filtering
    public List<Customer> filterOfLegalAgeCustomersInMemory() {
        Spliterator<Customer> customerSpliterator = customerRepository.findAll().spliterator();
        return StreamSupport.stream(customerSpliterator, false).filter(customer -> isOfLegalAge(customer)).collect(Collectors.toList());
    }

    public Iterable<Customer> filterOfLegalAgeCustomersInRepository() {
        return customerRepository.findAllByBirthdateIsBefore(LocalDate.now().minusYears(legalAge));
    }

    public Iterable<Customer> filterOfLegalAgeAndLastname(String lastname) {
        return customerRepository.findAllByBirthdateIsBeforeAndLastnameContainingIgnoreCase(LocalDate.now().minusYears(legalAge), lastname);
    }

    private boolean isOfLegalAge(Customer customers) {
        return customers.getBirthdate().isBefore(LocalDate.now().minusYears(legalAge));
    }

    //Make sure that a parameter searchFilter can be passed as a QueryParam to further filter the stream for all customers aged >= 18 and name startsWith searchFilter.
    public Iterable<Customer> filterOfLegalAgeAndName() {
        return customerRepository.findAllByBirthdateIsBefore(LocalDate.now().minusYears(18));
    }


    //Pass the parameter searchFilter "down" further the layers to optimize performance (not loading all customers, with only the ones containing the searchFilter and age > 18).

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer storeCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
