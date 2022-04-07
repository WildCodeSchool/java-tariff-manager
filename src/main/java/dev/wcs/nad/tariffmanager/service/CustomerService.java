package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.mapper.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EntityToDtoMapper entityToDtoMapper;

    public CustomerService(CustomerRepository customerRepository, EntityToDtoMapper entityToDtoMapper) {
        this.customerRepository = customerRepository;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    public List<CustomerDto> readAllCustomers() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer: customerRepository.findAll()) {
            customerDtos.add(entityToDtoMapper.customerToCustomerDto(customer));
        }
        return customerDtos;
    }
}
