package dev.wcs.nad.tariffmanager.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import dev.wcs.nad.tariffmanager.persistence.repository.TariffRepository;
import dev.wcs.nad.tariffmanager.service.ContractService;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;

public class ContractSteps {

    @Autowired CustomerRepository customerRepository;
    @Autowired ContractService contractService;
    @Autowired TariffRepository tariffRepository;

    @Quand("je crée un contrat pour le client {string} sur le produit {string}")
    @Transactional
    public void createContract(String customerName, String tariffName) {
        System.out.println("createContract - customerName=" + customerName + " tariffName=" + tariffName);
        Tariff tariff = tariffRepository.findByName(tariffName).get();
        Customer customer = getCustomerByName(customerName);
        contractService.assignContractToCustomer(customer.getId(), tariff.getId(),tariff.getPossibleOptions().stream().map(o -> o.getId()).findFirst().orElse(null));
    }

    @Alors("le client {string} a {int} contrat(s)")
    @Transactional
    public void assertCustomerContractsCount(String customerName, int contractCount) {
        System.out.println("assertCustomerContractsCount - customerName=" + customerName + " contractCount=" + contractCount);
        Customer customer = getCustomerByName(customerName);
        assertEquals(contractCount, customer.getContracts().size());
    }

    @Alors("le contrat {int} du client {string} a pour produit {string}")
    @Transactional
    public void assertCustomerContractTariffName(int contractIndex, String customerName, String tariffName) {
        System.out.println("assertCustomerContractTariffName - contractIndex=" + contractIndex + " customerName=" + customerName + " tariffName=" + tariffName);
        Customer customer = getCustomerByName(customerName);
        assertTrue(contractIndex <= customer.getContracts().size());
        assertEquals(tariffName, customer.getContracts().get(contractIndex - 1).getTariff().getName());
    }

    private Customer getCustomerByName(String name) {
        String firstName = name.split("[ ]")[0];
        String lastName = name.split("[ ]")[1];
        Customer customer = customerRepository.findByFirstnameAndLastname(firstName, lastName).get();
        return customer;
    }
}
