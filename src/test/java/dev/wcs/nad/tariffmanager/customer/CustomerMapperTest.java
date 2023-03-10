package dev.wcs.nad.tariffmanager.customer;

import com.github.javafaker.Faker;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.mapper.simple.CustomerMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CustomerMapperTest {

    private Faker faker = new Faker(Locale.US);

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void shouldMapDtoToEntity() {
        CustomerDto customerDto = createFakeCustomerDto();
        Customer entity = customerMapper.convertDtoToEntity(customerDto);
        assertThat(entity.getBirthdate()).isEqualTo(customerDto.getBirthdate());
    }

    @Test
    public void shouldMapEntityToDto() {
        // Challenge: Add the Entity to Dto mapping here.
        Customer entity = createFakeCustomerEntity();
        CustomerDto customerDto = customerMapper.convertEntityToDto(entity);
        assertThat(entity.getBirthdate()).isEqualTo(customerDto.getBirthdate());
    }

    private Customer createFakeCustomerEntity() {
        Customer.CustomerBuilder customerBuilder = Customer.builder();
        customerBuilder.birthdate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        customerBuilder.blocked(false);
        customerBuilder.firstname(faker.address().firstName());
        customerBuilder.lastname(faker.address().lastName());
        customerBuilder.passportNo(faker.idNumber().valid());
        return customerBuilder.build();
    }

    private CustomerDto createFakeCustomerDto() {
        CustomerDto.CustomerDtoBuilder customerDtoBuilder = CustomerDto.builder();
        customerDtoBuilder.birthdate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        customerDtoBuilder.firstname(faker.address().firstName());
        customerDtoBuilder.lastname(faker.address().lastName());
        customerDtoBuilder.passportNo(faker.idNumber().valid());
        return customerDtoBuilder.build();
    }

}
