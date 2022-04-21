package dev.wcs.nad.tariffmanager.adapter.rest.dto.tariff;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OptionDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal setup;

}
