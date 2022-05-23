package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.*;
import dev.wcs.nad.tariffmanager.persistence.repository.AddressRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.ContactRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;

    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepository, ContactRepository contactRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
    }

    public Iterable<Customer> readAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customerEntity) {
        return customerRepository.save(customerEntity);
    }

    public Customer assignAddress(Long customerId, Address mapAddressDto) {
        Address addressEntity = addressRepository.save(mapAddressDto);
        Customer customer = customerRepository.findById(customerId).get();
        Contact contact = customer.getContact();
        if (contact == null) {
            contact = new Contact();
        }
        contact.getAddresses().add(addressEntity);
        contact = contactRepository.save(contact);
        customer.setContact(contact);
        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer storeCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
