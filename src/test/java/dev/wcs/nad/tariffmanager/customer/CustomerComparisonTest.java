package dev.wcs.nad.tariffmanager.customer;

import dev.wcs.nad.tariffmanager.customer.collectiontesting.Customer;
import dev.wcs.nad.tariffmanager.customer.collectiontesting.SortableCustomer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerComparisonTest {

    @Test
    public void shouldDemonstrateComparisonWithComparable() {
        // Arrange

        // Note: This a customer from the testing package, not the customer used in the real application
        List<SortableCustomer> customers = new ArrayList<>();

        SortableCustomer joe = new SortableCustomer("Average Joe", LocalDate.now().minusYears(45));
        SortableCustomer cat = new SortableCustomer("Chatty Cathy", LocalDate.now().minusYears(25));
        SortableCustomer deb = new SortableCustomer("Debbie Downer", LocalDate.now().minusYears(37));
        SortableCustomer gus = new SortableCustomer("Gloomy Gus", LocalDate.now().minusYears(58));
        SortableCustomer sim = new SortableCustomer("Simple Simon", LocalDate.now().minusYears(19));

        // Insert into list not ordered lexically (pseudo-random)
        customers.add(deb);
        customers.add(gus);
        customers.add(joe);
        customers.add(sim);
        customers.add(cat);

        // Act
        // Do nothing, ie. the list stays in the order of insertions.

        // Assert
        assertThat(customers).containsSequence(deb, gus, joe, sim, cat);

        // Act
        // Note: In SortableCustomer, the compareTo method uses name for comparison
        Collections.sort(customers);

        // Assert
        // Now the sequence should be lexically sorted
        assertThat(customers).containsSequence(joe, cat, deb, gus, sim);
    }

    @Test
    public void shouldDemonstrateComparisonWithComparator() {
        // Arrange

        // Note: This a customer from the testing package, not the customer used in the real application
        List<SortableCustomer> customers = new ArrayList<>();

        SortableCustomer joe = new SortableCustomer("Average Joe", LocalDate.now().minusYears(45));
        SortableCustomer cat = new SortableCustomer("Chatty Cathy", LocalDate.now().minusYears(25));
        SortableCustomer deb = new SortableCustomer("Debbie Downer", LocalDate.now().minusYears(37));
        SortableCustomer gus = new SortableCustomer("Gloomy Gus", LocalDate.now().minusYears(58));
        SortableCustomer sim = new SortableCustomer("Simple Simon", LocalDate.now().minusYears(19));

        // Insert into list not ordered lexically (pseudo-random)
        customers.add(deb);
        customers.add(gus);
        customers.add(joe);
        customers.add(sim);
        customers.add(cat);

        // Act
        // Do nothing, ie. the list stays in the order of insertions.

        // Assert
        assertThat(customers).containsSequence(deb, gus, joe, sim, cat);

        Comparator<SortableCustomer> nameReversedComparator = new Comparator<SortableCustomer>() {
            @Override
            public int compare(SortableCustomer o1, SortableCustomer o2) {
                return o2.getName().compareTo(o1.getName());
            }
        };

        // Act
        // Note: In SortableCustomer, the compare method uses name reversed (first o2, then o1) for comparison, so it's reversed
        Collections.sort(customers, nameReversedComparator);

        // Assert
        // Now the sequence should be lexically sorted in reverse
        assertThat(customers).containsSequence(sim, gus, deb, cat, joe);

        Comparator<SortableCustomer> birthdayComparator = new Comparator<SortableCustomer>() {
            @Override
            public int compare(SortableCustomer o1, SortableCustomer o2) {
                return o1.getBirthdate().compareTo(o2.getBirthdate());
            }
        };

        // Act
        // Note: In SortableCustomer, the compareTo method uses birthdate for comparison
        Collections.sort(customers, birthdayComparator);

        // Assert
        // Now the sequence should be lexically sorted in reverse
        assertThat(customers).containsSequence(gus, joe, deb, cat, sim);

        // Act
        // Note: In SortableCustomer, the compareTo method uses name for comparison, but reversed
        Collections.sort(customers, birthdayComparator.reversed());
        assertThat(customers).containsSequence(sim, cat, deb, joe, gus);

        Comparator<SortableCustomer> birthdayComparatorRef = Comparator.comparing(SortableCustomer::getBirthdate);
        Collections.sort(customers, birthdayComparatorRef);
        assertThat(customers).containsSequence(gus, joe, deb, cat, sim);
    }

    @Test
    public void shouldImplementAllOptionsForComparator() {
        // Exercise of Quest "Java Reskilling Data-Structures 01: Comparable and Comparators

        // Arrange
        // Note: This a customer from the testing package, not the customer used in the real application
        List<SortableCustomer> customers = new ArrayList<>();

        SortableCustomer joe1 = new SortableCustomer("Average Joe", LocalDate.now().minusYears(45));
        SortableCustomer joe2 = new SortableCustomer("Average Joe", LocalDate.now().minusYears(25));
        SortableCustomer joe3 = new SortableCustomer("Average Joe", LocalDate.now().minusYears(37));
        SortableCustomer cat = new SortableCustomer("Chatty Cathy", LocalDate.now().minusYears(58));
        SortableCustomer sim = new SortableCustomer("Simple Simon", LocalDate.now().minusYears(19));

        // Insert into list not ordered lexically (pseudo-random)
        customers.add(joe1);
        customers.add(joe2);
        customers.add(joe3);
        customers.add(cat);
        customers.add(sim);

// Option 1: With Anonymous Classes
Comparator<SortableCustomer> nameComparator = new Comparator<SortableCustomer>() {
    @Override
    public int compare(SortableCustomer o1, SortableCustomer o2) {
        return o1.getName().compareTo(o2.getName());
    }
};

Comparator<SortableCustomer> birthdateComparator = new Comparator<SortableCustomer>() {
    @Override
    public int compare(SortableCustomer o1, SortableCustomer o2) {
        return o1.getBirthdate().compareTo(o2.getBirthdate());
    }
};

// combine the Comparators
Comparator<SortableCustomer> firstNamesThenBirthdateThenReverseAnon = nameComparator.thenComparing(birthdateComparator).reversed();
Collections.sort(customers, firstNamesThenBirthdateThenReverseAnon);
assertThat(customers).containsSequence(sim, cat, joe2, joe3, joe1);

// Option 2: with lambdas
Comparator<SortableCustomer> namesLambda = (c1, c2) -> (c1.getName().compareTo(c2.getName()));
Comparator<SortableCustomer> birthdateLambda = (c1, c2) -> (c1.getName().compareTo(c2.getName()));
Comparator<SortableCustomer> firstNamesThenBirthdateThenReverseLambda = namesLambda.thenComparing(birthdateLambda).reversed();
Collections.sort(customers, firstNamesThenBirthdateThenReverseLambda);
assertThat(customers).containsSequence(sim, cat, joe2, joe3, joe1);

// Option 3: with Method References
Comparator<SortableCustomer> firstNamesThenBirthdateThenReverseRef = Comparator.comparing(SortableCustomer::getName).thenComparing(SortableCustomer::getBirthdate).reversed();
Collections.sort(customers, firstNamesThenBirthdateThenReverseRef);
assertThat(customers).containsSequence(sim, cat, joe2, joe3, joe1);

    }

}
