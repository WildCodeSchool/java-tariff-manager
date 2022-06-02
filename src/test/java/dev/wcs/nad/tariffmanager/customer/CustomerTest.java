package dev.wcs.nad.tariffmanager.customer;

import dev.wcs.nad.tariffmanager.customer.model.*;
import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;
//import dev.wcs.nad.tariffmanager.customer.reporting.CustomerSales;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

    @Test
    public void shouldCreateDifferentCustomersWithDifferentDiscounts() {
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("1", "Achim", "achim@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer egon54 = new StandardCustomerNoPotential("1", "Egon", "egon@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));

        // getEmail is a method on Customer and can be invoked on any subclass
        assertThat(alfred22.getEmail()).isEqualTo("alfred@web.de");
        assertThat(achim24.getEmail()).isEqualTo("achim@web.de");
        assertThat(egon54.getEmail()).isEqualTo("egon@web.de");

        // Verify that alfred22 is a Customer (abstract class)
        assertThat(alfred22).isInstanceOf(Customer.class);
        // Verify that alfred22 is a VICustomer (extending, specific subclass)
        assertThat(alfred22).isInstanceOf(VICustomer.class);

        // Polymorphic call of calculateDiscountedPrice calls the method on the subclass instance
        assertThat(alfred22.calculateDiscountedPrice(100)).isEqualTo(90);
        assertThat(achim24.calculateDiscountedPrice(100)).isEqualTo(95);
        assertThat(egon54.calculateDiscountedPrice(100)).isEqualTo(100);
    }

    @Test
    public void shouldImplementFilterOnGenericPropertiesLikeName() {
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("1", "Achim", "achim@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer egon54 = new StandardCustomerNoPotential("1", "Egon", "egon@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));
        List<Customer> customers = List.of(alfred22, achim24, egon54);

        // Use Streams for Filtering
        List<Customer> customersOlderThan22 = customers.stream().filter(customer -> customer.getBirthDate().plusYears(22).isBefore(LocalDate.now())).collect(Collectors.toList());

        // Use Streams for filtering names starting with letter "A"
        List<Customer> customerStartingWithA;

        assertThat(customersOlderThan22).hasSize(2);
        // Uncomment after implementing the customerStartingWithA filter for the Stream of Customers
        // assertThat(customerStartingWithA).hasSize(2);
    }

    @Test
    public void shouldUseCustomerSalesReporting() {
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("2", "Achim", "achim@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(101));
        Customer egon54 = new StandardCustomerNoPotential("3", "Egon", "egon@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(102));
        List<Customer> customers = List.of(alfred22, achim24, egon54);

        // Challenge: Uncomment the comments and make sure the test passes.
        List<Customer> filteredCustomers = null; // CustomerSales.filterCustomersForType(customers, CustomerType.VI);
        assertThat(filteredCustomers.get(0)).isEqualTo(alfred22);

        List<Customer> dateSortedCustomers = null; // CustomerSales.sortByLastPurchase(customers);
        assertThat(dateSortedCustomers.get(0)).isEqualTo(egon54);

        List<Customer> nameSortedCustomers = null; // CustomerSales.sortByName(customers);
        assertThat(nameSortedCustomers.get(0)).isEqualTo(achim24);
    }

    @Test
    public void shouldImplementLogicOnBaseMethods() {
        // Arrange
        Customer alfred22 = new VICustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer achim24 = new SpecialCustomer("1", "Achim", "achim@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer egon54 = new StandardCustomerNoPotential("1", "Egon", "egon@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));

        // Act
        List<Customer> customers = List.of(alfred22, achim24, egon54);
        // This collection is filtered using a conventional for-loop
        for (Customer customer : customers) {
            // Only if necessary we check for specific sub class
            if (customer instanceof VICustomer) {
                System.out.println("Mail of VICustomer: " + customer.getEmail());
            }
            // Better way is to include the logic into the class itself
            if (customer.isRelevantForMailing()) {
                System.out.println("Mail of VICustomer: " + customer.getEmail());
            }
        }

    }

    @Test
    public void shouldTestNewEmployeeCustomer() {
        // Arrange
        Customer employee1 = new EmployeeCustomer("1", "Alfred", "alfred@web.de", LocalDate.now().minusYears(22), LocalDate.now().minusDays(100));
        Customer employee2 = new EmployeeCustomer("2", "Achim", "achim@web.de", LocalDate.now().minusYears(24), LocalDate.now().minusDays(100));
        Customer employee3 = new StandardCustomerNoPotential("3", "Egon", "egon@web.de", LocalDate.now().minusYears(54), LocalDate.now().minusDays(100));

        // Act & Assert
        assertThat(employee1.calculateDiscountedPrice(100)).isEqualTo(85);
        assertThat(employee2.calculateDiscountedPrice(100)).isEqualTo(85);
        assertThat(employee3.calculateDiscountedPrice(100)).isEqualTo(100);
    }

}
