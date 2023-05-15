package dev.wcs.nad.tariffmanager.cucumber;

import static org.junit.Assert.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dev.wcs.nad.tariffmanager.persistence.entity.Address;
import dev.wcs.nad.tariffmanager.persistence.entity.Contact;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.repository.AddressRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.ContactRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import dev.wcs.nad.tariffmanager.service.CustomerService;
import io.cucumber.java.fr.*;

public class CustomerSteps {

    @Autowired CustomerRepository customerRepository;
    @Autowired ContactRepository contactRepository;
    @Autowired CustomerService customerService;
    @Autowired AddressRepository addressRepository;

    @Autowired ParamHelper paramHelper;

    @Etantdonné("le(s) client(s) suivant(s):")
    public void givenCustomers(List<List<String>> data) {
        System.out.println("customersData:  " + data);

        for (List<String> customerData : data) {
            var customer = new Customer();
            customer.setFirstname(customerData.get(0));
            customer.setLastname(customerData.get(1));

            customerRepository.saveAndFlush(customer);
        }
    }


    @Quand("j'ajoute une adresse {int} {string}, {string} {string} pour le client {string}")
    @Transactional
    public void assignAddress(int streeNumber, String streetName, String postcode, String city, String customerName) {
        System.out.println("assignAddress - streeNumber=" + streeNumber + " streetName=" + streetName + " postcode=" + postcode + " city=" + city + " customerName=" + customerName);
        var address = new Address();
        address.setStreet(streetName);
        address.setNumber("" + streeNumber);
        address.setPostalcode(postcode); 
        address.setCity(city);
        addressRepository.save(address);

        Customer customer = paramHelper.getCustomerByName(customerName);
        customerService.assignAddress(customer.getId(), address);
    }
    
    @Alors("le client {string} a {int} adresse")
    @Transactional
    public void assertCustomerAddressesCount(String customerName, int count) {
        System.out.println("assertClientAddressesCount - customerName=" + customerName + " count=" + count);

        Customer customer = paramHelper.getCustomerByName(customerName);
        assertEquals(count, customer.getContact().getAddresses().size());
    }

    @Alors("la ville de l'adresse {int} du client {string} est {int} {string}, {string} {string}")
    @Transactional
    public void assertCustomerAddress(int addressIndex, String customerName, int streeNumber, String streetName, String postcode, String city) {
        System.out.println("assertCustomerAddress - customerName=" + customerName + " streeNumber=" + streeNumber + " streetName=" + streetName + " postcode=" + postcode + " city=" + city);

        Customer customer = paramHelper.getCustomerByName(customerName);
        Address address = customer.getContact().getAddresses().get(addressIndex - 1);
        assertEquals("" + streeNumber, address.getNumber());
        assertEquals(streetName, address.getStreet());
        assertEquals(postcode, address.getPostalcode());
        assertEquals(city, address.getCity());
    }
}
