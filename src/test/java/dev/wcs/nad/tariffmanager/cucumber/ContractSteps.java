package dev.wcs.nad.tariffmanager.cucumber;

import static org.junit.Assert.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import dev.wcs.nad.tariffmanager.persistence.repository.CustomerRepository;
import io.cucumber.java.fr.*;
public class ContractSteps {

    @Autowired
    CustomerRepository customerRepository;

    @Quand("je crée un contrat pour le client {string} sur le produit {string}")
    public void selectOldestFire(String customerName, String tariffName) {
        System.out.println("!!!> " + customerName + " === " + tariffName);
    }

    @Alors("le client {string} a {int} contrat(s)")
    public void assertFires(String customerName,int contractCount) {
        System.out.println("assertFires - count=" + contractCount + " name=" + customerName);
    }
    @Alors("le contrat {int} du client {string} a pour produit {string}")
    public void assertFires2(int contractIndex, String customerName,String tariffName) {
        System.out.println("assertFires2 - index="+contractIndex+" customerName=" + customerName + " name=" + tariffName);
    }
}
