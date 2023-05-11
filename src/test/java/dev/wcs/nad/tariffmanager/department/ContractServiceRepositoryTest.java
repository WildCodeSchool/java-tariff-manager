package dev.wcs.nad.tariffmanager.department;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.repository.ContractRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ContractServiceRepositoryTest {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;
    private StopWatch watch = new StopWatch();

    @BeforeEach
    public void setup() {
        contractRepository.deleteAll();
        customerRepository.deleteAll();
        testCustomer = new Customer();
        testCustomer.setFirstname("Emmanuel");
        customerRepository.saveAndFlush(testCustomer);
    }

    @Test
    public void contractImportPerformanceTest() {
        List<Contract> contracts = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            var contract = new Contract();
            contract.setCustomer(testCustomer);
            contracts.add(contract);
        }

        watch.start();
        contractRepository.saveAllAndFlush(contracts);
        watch.stop();

        long count = contractRepository.count();
        assertEquals(1000L, count);

        System.out.println("ensure bulk insert less than 500ms");
        assertTrue(watch.getTime() < 500, "bulk insert is too slow, over 500ms");
        
    }
}
