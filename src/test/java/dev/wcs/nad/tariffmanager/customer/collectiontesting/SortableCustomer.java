package dev.wcs.nad.tariffmanager.customer.collectiontesting;

import java.time.LocalDate;

// This customer class is used for Comparison testing.
public class SortableCustomer implements Comparable<SortableCustomer> {

    private String name;
    private LocalDate birthdate;

    public SortableCustomer(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(SortableCustomer that) {
        return this.name.compareTo(that.getName());
    }

    @Override
    public String toString() {
        return getName();
    }
}
