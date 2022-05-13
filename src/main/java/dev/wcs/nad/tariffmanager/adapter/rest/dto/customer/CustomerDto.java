package dev.wcs.nad.tariffmanager.adapter.rest.dto.customer;

import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomerDto {

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String passportNo;
    private CustomerType customerType;
    @Builder.Default
    private List<AddressDto> addresses = new ArrayList<>();
    @Builder.Default
    private List<ContractDto> contractInfo = new ArrayList<>();

}