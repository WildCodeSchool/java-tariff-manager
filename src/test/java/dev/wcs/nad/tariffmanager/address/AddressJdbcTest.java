package dev.wcs.nad.tariffmanager.address;

import dev.wcs.nad.tariffmanager.TariffManagerApplication;
import dev.wcs.nad.tariffmanager.persistence.entity.Address;
import dev.wcs.nad.tariffmanager.persistence.jdbc.AddressLegacyDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(TariffManagerApplication.class)
public class AddressJdbcTest {

    @Autowired
    private AddressLegacyDao addressLegacyDao;

    @Test
    public void shouldLoadAddressWithId3() {
        Optional<Address> secondAddress = addressLegacyDao.getByIdJava7Syntax(3);
        assertThat(secondAddress.isEmpty()).isFalse();
        assertThat(secondAddress.get().getId().toString().equals("3")).isTrue();
    }

    @Test
    public void shouldLoadAddressWithId5() {
        Optional<Address> address = addressLegacyDao.getByIdJava7Syntax(5);
        assertThat(address.isEmpty()).isFalse();
        assertThat(address.get().getId().toString().equals("5")).isTrue();
    }

    @Test
    public void shouldReturnEmptyIfAddressNotFound() {
        Optional<Address> address = addressLegacyDao.getByIdJava7Syntax(123);
        assertThat(address.isEmpty()).isTrue();
    }
}