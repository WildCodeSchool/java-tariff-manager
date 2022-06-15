package dev.wcs.nad.tariffmanager.customer.collectiontesting;

import java.util.Objects;

// This customer class is used for Collection testing.
public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Override toString to facilitate readable test outputs
    @Override
    public String toString() {
        return this.name;
    }

    // We have to override equals and hashCode to avoid duplicates
    @Override
    public boolean equals(Object customerToCompare) {
        if (this == customerToCompare) return true;
        if (customerToCompare == null || getClass() != customerToCompare.getClass()) return false;
        Customer customer = (Customer) customerToCompare;
        return Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
