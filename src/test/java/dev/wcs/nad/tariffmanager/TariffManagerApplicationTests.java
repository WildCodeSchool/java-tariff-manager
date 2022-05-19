package dev.wcs.nad.tariffmanager;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.customer.reporting.CustomerImporter;
import dev.wcs.nad.tariffmanager.persistence.jdbc.CustomerLegacyDao;
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

	@Autowired
	private CustomerLegacyDao customerLegacyDao;

	@Test
	void testCustomerImportWithInjectedConfiguredSpringBean() {
		File customerCsv = new File("src/test/resources/testdata/customer.csv");
		List<Customer> importedCustomers = customerImporter.importCustomers(customerCsv);
		assertThat(importedCustomers).hasSize(100);
	}

	@Test
	void testShouldMapCustomerWithJdbc() {
		customerLegacyDao.getByIdJava7Syntax(1513);
	}

}
