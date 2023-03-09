package dev.wcs.nad.tariffmanager.customer;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.persistence.jdbc.CustomerLegacyDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerJdbcTest {

    @Autowired
    private CustomerLegacyDao customerLegacyDao;

    @Test
    public void shouldLoadCustomerWithId1() {
        Optional<Customer> firstCustomer = customerLegacyDao.getByIdJava7Syntax(1);
        assertThat(firstCustomer.isEmpty()).isFalse();
        assertThat(firstCustomer.get().getId().equals("1")).isTrue();
    }


}
