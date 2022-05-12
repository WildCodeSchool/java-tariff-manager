package dev.wcs.nad.tariffmanager;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.customer.reporting.CustomerImporter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TariffManagerApplicationTests {

	@Autowired
	private CustomerImporter customerImporter;

	@Test
	void testCustomerImportWithInjectedConfiguredSpringBean() {
		File customerCsv = new File("src/test/resources/testdata/customer.csv");
		List<Customer> importedCustomers = customerImporter.importCustomers(customerCsv);
		assertThat(importedCustomers).hasSize(99);
	}

}
