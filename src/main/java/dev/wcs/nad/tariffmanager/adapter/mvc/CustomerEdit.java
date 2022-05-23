package dev.wcs.nad.tariffmanager.adapter.mvc;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/public/customer")
public class CustomerEdit {

    private final CustomerService customerService;
    private final EntityToDtoMapper entityToDtoMapper;

    public CustomerEdit(CustomerService customerService, EntityToDtoMapper entityToDtoMapper) {
        this.customerService = customerService;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("now", new Date().toInstant());

        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer: customerService.readAllCustomers()) {
            customerDtos.add(entityToDtoMapper.customerToCustomerDto(customer));
        }
        model.addAttribute("customers", customerDtos);

        return "customerListView";
    }

    @GetMapping("/edit/{id}")
    public String editview(Model model, @PathVariable Long id) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        model.addAttribute("customer", customer.get());
        model.addAttribute("id", id);
        return "customerEditView";
    }

    @PostMapping("/edit/{id}")
    public String editupdate(CustomerDto customerDto, Model model, @PathVariable Long id) {
        model.addAttribute("customer", customerDto);
        model.addAttribute("id", id);
        return "customerEditForm";
    }

    @PostMapping("/update/{id}")
    public String update(CustomerDto customerDto, Model model, @PathVariable Long id) {
        Optional<Customer> customer = customerService.findCustomerById(id);
        customer.get().setFirstname(customerDto.getFirstname());
        customer.get().setLastname(customerDto.getLastname());
        customer.get().setBirthdate(customerDto.getBirthdate());
        Customer storedCustomer = customerService.storeCustomer(customer.get());
        model.addAttribute("customer", storedCustomer);
        model.addAttribute("id", id);
        return "customerEditViewFragment";
    }

}
