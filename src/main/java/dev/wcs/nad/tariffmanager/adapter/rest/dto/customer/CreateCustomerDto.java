package dev.wcs.nad.tariffmanager.adapter.rest.dto.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateCustomerDto {

    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String passportNo;

}