package dev.wcs.nad.tariffmanager.service;

import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.entity.Option;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.ContractRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.OptionRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    private final ContractRepository contractRepository;
    private final TariffRepository tariffRepository;
    private final OptionRepository optionRepository;
    private final CustomerRepository customerRepository;

    public ContractService(ContractRepository contractRepository, TariffRepository tariffRepository, OptionRepository optionRepository, CustomerRepository customerRepository) {
        this.contractRepository = contractRepository;
        this.tariffRepository = tariffRepository;
        this.optionRepository = optionRepository;
        this.customerRepository = customerRepository;
    }

    public List<Contract> readContractsForUser(Long userId) {
        return contractRepository.findContractsByCustomer_IdIs(userId);
    }

    public Contract assignContractToCustomer(Long customerId, Long tariffId, Long optionId) {
        Tariff tariff = tariffRepository.findById(tariffId).get();
        Option option = optionRepository.findById(optionId).get();
        Customer customer = customerRepository.findById(customerId).get();
        Contract contract = new Contract();
        contract.setTariff(tariff);
        contract.getOptions().add(option);
        customer.addContract(contract);
        return contractRepository.save(contract);
    }
}
