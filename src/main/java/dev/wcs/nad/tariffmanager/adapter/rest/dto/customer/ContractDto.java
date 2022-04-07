package dev.wcs.nad.tariffmanager.adapter.rest.dto.customer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContractDto {

    private String tariff;
    private List<String> options;

}
