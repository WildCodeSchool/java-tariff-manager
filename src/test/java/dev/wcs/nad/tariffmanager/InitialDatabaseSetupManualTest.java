package dev.wcs.nad.tariffmanager;

import com.github.javafaker.Faker;
import dev.wcs.nad.tariffmanager.persistence.entity.*;
import dev.wcs.nad.tariffmanager.persistence.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Locale;

@SpringBootTest
public class InitialDatabaseSetupManualTest {

    private Faker faker = new Faker(Locale.US);

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ContactRepository contactRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private ContractRepository contractRepository;
    @Autowired private OptionRepository optionRepository;
    @Autowired private TariffRepository tariffRepository;

    @Test
    public void setupDatabase() {
        // Create customers
        Customer[] customers = new Customer[10];
        for (int i=0; i<10; i++) {
            Customer customer = createFakeCustomer();
            Address address = createFakeAddress();
            Contact contact = createFakeContact();
            customer.setContact(contact);
            contact.addAddress(address);
            addressRepository.save(address);
            customer = customerRepository.save(customer);
            customers[i] = customer;
        }
        // Create Tariffs & Contracts & Options
        for (int i=0; i<10; i++) {
            Option option = createFakeOption();
            Tariff tariff = createFakeTariff();
            tariff.getPossibleOptions().add(option);
            tariff = tariffRepository.save(tariff);
            Contract contract = createFakeContract();
            contract.setTariff(tariff);
            contract.setCustomer(customers[i]);
            contract.getOptions().add(option);
            contractRepository.save(contract);
        }
    }

    //@Test
    public void clearDatabase() {
        customerRepository.deleteAll();
        contactRepository.deleteAll();
        addressRepository.deleteAll();
        contractRepository.deleteAll();
        tariffRepository.deleteAll();
        optionRepository.deleteAll();
    }

    private Customer createFakeCustomer() {
        Customer customer = new Customer();
        customer.setBirthdate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        customer.setBlocked(false);
        customer.setFirstname(faker.address().firstName());
        customer.setLastname(faker.address().lastName());
        customer.setPassportNo(faker.idNumber().valid());
        return customerRepository.save(customer);
    }

    private Contact createFakeContact() {
        Contact contact = new Contact();
        contact.setEmail(faker.internet().emailAddress());
        contact.setPhone(faker.phoneNumber().phoneNumber());
        return contactRepository.save(contact);
    }

    private Address createFakeAddress() {
        Address address = new Address();
        address.setCity(faker.address().city());
        address.setStreet(faker.address().streetAddress());
        address.setPostalcode(faker.address().zipCode());
        address.setNumber(faker.address().buildingNumber());
        return addressRepository.save(address);
    }

    private Option createFakeOption() {
        Option option = new Option();
        option.setSetup(new BigDecimal(faker.commerce().price().replaceAll(",", ".")));
        option.setName((faker.commerce().productName() + " v " + faker.number().randomDouble(5, 10, 20)).toLowerCase());
        option.setPrice(new BigDecimal(faker.commerce().price().replaceAll(",", ".")));
        return optionRepository.save(option);
    }

    private Tariff createFakeTariff() {
        Tariff tariff = new Tariff();
        tariff.setName(faker.commerce().productName().replaceAll(" ", "-").toUpperCase());
        tariff.setPrice(new BigDecimal(faker.commerce().price().replaceAll(",", ".")));
        return tariffRepository.save(tariff);
    }

    private Contract createFakeContract() {
        Contract contract = new Contract();
        return contractRepository.save(contract);
    }

}
