package dev.wcs.nad.tariffmanager.adapter.rest.dto.contract;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.tariff.OptionDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.tariff.TariffDto;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class ContractInfoDto {

    private TariffDto tariff;
    private CustomerDto customer;
    private Set<OptionDto> options = new LinkedHashSet<>();

}
