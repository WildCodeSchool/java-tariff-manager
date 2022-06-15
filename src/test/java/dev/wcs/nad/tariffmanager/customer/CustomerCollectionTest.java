package dev.wcs.nad.tariffmanager.customer;

import dev.wcs.nad.tariffmanager.customer.collectiontesting.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerCollectionTest {

    @Test
    public void shouldDemostrateJavaCollectionsWithLists() {
        // Arrange

        // Instantiate a List implementation: ArrayList
        // Note: This a customer from the testing package, not the customer used in the real application
        List<Customer> customers = new ArrayList<>();
        // Add some customers to the list
        Customer joe = new Customer("Average Joe");
        Customer cat = new Customer("Chatty Cathy");
        Customer deb = new Customer("Debbie Downer");
        Customer gus = new Customer("Gloomy Gus");
        Customer sim = new Customer("Simple Simon");
        // Insert customers to the list
        customers.add(joe);
        customers.add(cat);
        customers.add(deb);
        customers.add(gus);
        customers.add(sim);
        // Modify customers from outside of the list (which still impact the correlated elements within the list)
        joe.setName("Joe Blow");
        // Iterating over customers
        for (Customer customer : customers) {
            System.out.println(customer.getName());
        }
        // Remove customers from the list
        // Remove Joe as object
        customers.remove(joe);
        // Then remove Cathy who is in index 0 now
        customers.remove(0);

        // Assert
        assertThat(customers).doesNotContain(joe);
        assertThat(customers).doesNotContain(cat);
        assertThat(customers).containsExactly(deb, gus, sim);
    }

    @Test
    public void shouldDemostrateJavaCollectionsWithSets() {
        // Arrange

        // Instantiate a Set implementation: HashSet
        // Note: This a customer from the testing package, not the customer used in the real application
        Set<Customer> customers = new HashSet<>();
        // Add some customers to the list
        Customer joe = new Customer("Average Joe");
        Customer cat = new Customer("Chatty Cathy");
        Customer deb = new Customer("Debbie Downer");
        Customer gus = new Customer("Gloomy Gus");
        Customer sim = new Customer("Simple Simon");
        // Add customers to the set
        customers.add(joe);
        customers.add(cat);
        customers.add(deb);
        customers.add(gus);
        customers.add(sim);
        // Only one Joe should be included in the set, try to add here
        customers.add(joe);
        // Modify customers from outside of the set (which still impact the correlated elements within the set)
        joe.setName("Joe Blow");
        // Iterating over customers
        for (Customer customer : customers) {
            System.out.println(customer.getName());
        }
        // Remove customers from the set
        // Remove Cathy as object
        customers.remove(cat);
        // We don't remove woth index as the Set is not ordered: we don't know which element is removed!
        // customers.remove(1);

        // Assert
        assertThat(customers).doesNotContain(cat);
        assertThat(customers).containsExactlyInAnyOrder(joe, deb, gus, sim);
    }

}
