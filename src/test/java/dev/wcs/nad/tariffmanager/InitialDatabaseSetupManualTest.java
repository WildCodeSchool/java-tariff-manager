package dev.wcs.nad.tariffmanager;

import com.github.javafaker.Faker;
import dev.wcs.nad.tariffmanager.persistence.entity.*;
import dev.wcs.nad.tariffmanager.persistence.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    @Autowired private JdbcTemplate jdbcTemplate;

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
        createUsers();
    }

    private void createUsers() {
        String sql = """
            INSERT INTO SUSER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, PARSEDATETIME('01-01-2016', 'dd-MM-yyyy'));
            INSERT INTO SUSER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
            INSERT INTO SUSER (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, PARSEDATETIME('01-01-2016','dd-MM-yyyy'));
            INSERT INTO AUTHORITY (ID, NAME) VALUES (1, 'ROLE_USER');
            INSERT INTO AUTHORITY (ID, NAME) VALUES (2, 'ROLE_ADMIN');
            INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 1);
            INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (1, 2);
            INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (2, 1);
            INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) VALUES (3, 1);
            """;
        jdbcTemplate.execute(sql);
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
