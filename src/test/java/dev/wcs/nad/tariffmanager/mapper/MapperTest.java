package dev.wcs.nad.tariffmanager.mapper;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.customer.AddressDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapperImpl;
import org.junit.jupiter.api.Test;

public class MapperTest {

    @Test
    public void shouldMapDtoToEntity() {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddress("addr1");
        System.out.println(new EntityToDtoMapperImpl().mapAddressDto(addressDto));
    }
}
