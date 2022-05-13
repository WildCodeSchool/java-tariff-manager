package dev.wcs.nad.tariffmanager.mapper.simple;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto convertEntityToDto(Customer customerEntity) {
        // We use Lombok Builder (Pattern) here
        CustomerDto.CustomerDtoBuilder customerDtoBuilder = CustomerDto.builder();
        customerDtoBuilder.firstname(customerEntity.getFirstname());
        customerDtoBuilder.lastname(customerEntity.getLastname());
        customerDtoBuilder.birthdate(customerEntity.getBirthdate());
        customerDtoBuilder.passportNo(customerEntity.getPassportNo());
        // Depending on the use case for the DTOs, we could also map inner objects
        // like Address, Contact, etc. But in this case, we are only interested in the customer data.
        // By calling build, the object is finalized in the Builder and returned.
        return customerDtoBuilder.build();
    }

    public Customer convertDtoToEntity(CustomerDto customerDto) {
        // How can a customer be created here and why is it difficult? Hint: What type of customer should be created here?
        Customer.CustomerBuilder customerBuilder = Customer.builder();
        customerBuilder.customerType(customerDto.getCustomerType());
        customerBuilder.birthdate(customerDto.getBirthdate());
        customerBuilder.firstname(customerDto.getFirstname());
        customerBuilder.lastname(customerDto.getLastname());
        customerBuilder.passportNo(customerDto.getPassportNo());
        return customerBuilder.build();
    }

}
