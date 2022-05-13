package dev.wcs.nad.tariffmanager.mapper.mapstruct;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.contract.ContractInfoDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.AddressDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CreateCustomerDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.CustomerDto;
import dev.wcs.nad.tariffmanager.persistence.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
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

    Customer createCustomerDtoToCustomer(CreateCustomerDto createCustomerDto);

    default Tariff tariffNameToTariff(String tariffName) {
        Tariff tariff = new Tariff();
        tariff.setName(tariffName);
        return tariff;
    }

    default Set<Option> stringsToOptionSet(List<String> optionNames) {
        Set<Option> generatedOptions = new HashSet<>();
        for (String optionName : optionNames) {
            Option option = new Option();
            option.setName(optionName);
            generatedOptions.add(option);
        }
        return generatedOptions;
    }

    List<ContractInfoDto> contractsToContractInfosDto(List<Contract> customers);

    default String mapAddress(Address address) {
        return address.getStreet() + " " + address.getNumber() + " " + address.getPostalcode() + " " + address.getCity();
    }

    default Address mapAddressDto(AddressDto addressDto) {
        Address address = new Address();
        address.setStreet(addressDto.getAddress());
        return address;
    }

    default String mapTariffName(Tariff tariff) {
        return tariff.getName();
    }

    default List<String> mapOptionName(Set<Option> options) {
        return options.stream().map(Option::getName).collect(Collectors.toList());
    }

}
