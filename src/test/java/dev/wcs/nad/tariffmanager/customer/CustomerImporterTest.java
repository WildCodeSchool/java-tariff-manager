package dev.wcs.nad.tariffmanager.customer;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.customer.importing.CustomerImporter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerImporterTest {

    @Test
    public void shouldImportCsvToCustomerObjectModelNoSpringContext() {
        File customerCsv = new File("src/test/resources/testdata/customer.csv");
        // We are manually instantiating the CustomerImporter here as we have no Spring context in plain Unit tests
        CustomerImporter customerImporter = new CustomerImporter(15, 10);
        List<Customer> importedCustomers = customerImporter.importCustomers(customerCsv);
        assertThat(importedCustomers.get(0).calculateDiscountedPrice(100)).isEqualTo(90);
        assertThat(importedCustomers.get(1).calculateDiscountedPrice(100)).isEqualTo(85);
    }

}