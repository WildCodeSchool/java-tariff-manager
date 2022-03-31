package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.persistence.repository.ContractRepository;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Iterable<Contract> readContractsForUser(String userId) {
        Iterable<Contract> contracts = contractRepository.findAll();
        return contracts;
    }
}
