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
@RequestMapping("/public/private")
public class PrivateView {

    private final CustomerService customerService;
    private final EntityToDtoMapper entityToDtoMapper;

    public PrivateView(CustomerService customerService, EntityToDtoMapper entityToDtoMapper) {
        this.customerService = customerService;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    @GetMapping("/view")
    public String list(Model model) {
        model.addAttribute("now", new Date().toInstant());
        return "private";
    }

}
