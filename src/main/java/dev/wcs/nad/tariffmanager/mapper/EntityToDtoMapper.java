package dev.wcs.nad.tariffmanager.mapper;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.persistence.entity.Address;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring"
)
public interface EntityToDtoMapper {

    @Mapping(source = "contracts", target = "contractInfo")
    @Mapping(source = "contact.addresses", target = "addresses")
    CustomerDto customerToCustomerDto(Customer customer);

    default String mapAddress(Address address) {
        return address.getStreet() + " " + address.getNumber() + " " + address.getPostalcode() + " " + address.getCity();
    }

    default String mapTariffName(Tariff tariff) {
        return tariff.getName();
    }

    default List<String> mapOptionName(Set<Option> options) {
        return options.stream().map(Option::getName).collect(Collectors.toList());
    }

}
