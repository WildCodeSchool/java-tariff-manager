package dev.wcs.nad.tariffmanager.adapter.rest.dto.customer;

import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String passportNo;
    private CustomerType customerType;
    @Builder.Default
    private List<AddressDto> addresses = new ArrayList<>();
    @Builder.Default
    private List<ContractDto> contractInfo = new ArrayList<>();

}