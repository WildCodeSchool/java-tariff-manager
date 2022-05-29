package dev.wcs.nad.tariffmanager.adapter.rest;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.contract.ContractInfoDto;
import dev.wcs.nad.tariffmanager.adapter.rest.dto.contract.TariffAndOptionAssignmentDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {

    private final ContractService contractService;
    private final EntityToDtoMapper entityToDtoMapper;

    public ContractController(ContractService contractService, EntityToDtoMapper entityToDtoMapper) {
        this.contractService = contractService;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    @GetMapping("/api/contracts/{customerId}")
    public List<ContractInfoDto> displayCustomers(@PathVariable("customerId") Long customerId) {
        return entityToDtoMapper.contractsToContractInfosDto(contractService.readContractsForUser(customerId));
    }

    @PostMapping("/api/contracts/{userId}")
    public ResponseEntity<Void> assignTariffAndOption(@RequestBody TariffAndOptionAssignmentDto tariffAndOptionAssignmentDto) {
        Contract contract = contractService.assignContractToCustomer(tariffAndOptionAssignmentDto.getCustomerId(), tariffAndOptionAssignmentDto.getTariffId(), tariffAndOptionAssignmentDto.getOptionId());
        return ResponseEntity.ok().build();
    }
}
