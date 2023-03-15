package dev.wcs.nad.tariffmanager;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import dev.wcs.nad.tariffmanager.identity.user.UserRepository;
import dev.wcs.nad.tariffmanager.persistence.entity.Address;
import dev.wcs.nad.tariffmanager.persistence.entity.Contact;
import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.entity.Department;
import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.identity.entity.User;
import dev.wcs.nad.tariffmanager.persistence.repository.AddressRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.ContactRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.ContractRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.DepartmentRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.OptionRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;

@Service
public class DBInitializer {

    private Faker faker = new Faker(Locale.FRANCE);

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ContactRepository contactRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private ContractRepository contractRepository;
    @Autowired private OptionRepository optionRepository;
    @Autowired private TariffRepository tariffRepository;
    @Autowired private DepartmentRepository departmentRepository;
    
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    private final static int FAKE_COUNT = 50;

    public void setupDatabaseIfNotDoneYet() {
        userRepository.deleteAll();
        if (userRepository.count() <= 0) {
            var user = new User("user@wcs.com", passwordEncoder.encode("user"));
            var admin = new User("admin@wcs.com", passwordEncoder.encode("admin"));
            var backofficeUser = new User("backoffice@wcs.com", passwordEncoder.encode("backoffice"));
            userRepository.save(user);
            userRepository.save(admin);
            userRepository.save(backofficeUser);
        }

        if (customerRepository.count() > 0) {
            return;
        }
        // Create customers
        Customer[] customers = new Customer[FAKE_COUNT];
        for (int i=0; i<FAKE_COUNT; i++) {
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
        for (int i=0; i<FAKE_COUNT; i++) {
            Option option = createFakeOption();
            Department department = createFakeDepartment();
            Tariff tariff = createFakeTariff();
            department.addTariff(tariff);
            tariff.getPossibleOptions().add(option);
            tariff = tariffRepository.save(tariff);
            Contract contract = createFakeContract();
            contract.setTariff(tariff);
            contract.setCustomer(customers[i]);
            contract.getOptions().add(option);
            contractRepository.save(contract);
        }
    }

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

    private Department createFakeDepartment() {
        Department department = new Department();
        department.setName(faker.company().buzzword());
        return departmentRepository.save(department);
    }

}
