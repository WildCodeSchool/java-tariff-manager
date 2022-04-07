package dev.wcs.nad.tariffmanager.adapter.rest.dto.customer;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDto {

    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String passportNo;
    private List<AddressDto> addresses = new ArrayList<>();
    private List<ContractDto> contractInfo = new ArrayList<>();

}