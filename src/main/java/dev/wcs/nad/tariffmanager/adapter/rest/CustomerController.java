package dev.wcs.nad.tariffmanager.adapter.rest;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.AddressDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CreateCustomerDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;
    private final EntityToDtoMapper entityToDtoMapper;

    public CustomerController(CustomerService customerService, EntityToDtoMapper entityToDtoMapper) {
        this.customerService = customerService;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    @GetMapping("/api/customers")
    public List<CustomerDto> displayCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer: customerService.readAllCustomers()) {
            customerDtos.add(entityToDtoMapper.customerToCustomerDto(customer));
        }
        return customerDtos;
    }

    @PostMapping("/api/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        CustomerDto customerDto = entityToDtoMapper.customerToCustomerDto(customerService.createCustomer(entityToDtoMapper.createCustomerDtoToCustomer(createCustomerDto)));
        return ResponseEntity.ok(customerDto);
    }

    @PutMapping("/api/customers/{id}")
    public ResponseEntity<CustomerDto> assignAddress(@PathVariable("id") Long customerId, @RequestBody AddressDto addressDto) {
        Customer customerEntity = customerService.assignAddress(customerId, entityToDtoMapper.mapAddressDto(addressDto));
        return ResponseEntity.ok(entityToDtoMapper.customerToCustomerDto(customerEntity));
    }
}
