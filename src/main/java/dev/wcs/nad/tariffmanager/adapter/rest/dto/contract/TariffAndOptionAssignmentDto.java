package dev.wcs.nad.tariffmanager.adapter.rest.dto.contract;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffAndOptionAssignmentDto {

    private Long tariffId;
    private Long optionId;
    private Long customerId;

}
