package dev.wcs.nad.tariffmanager.customer.model;

import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;

import java.time.LocalDate;

public class StandardCustomerWithPotential extends StandardCustomer {

    public StandardCustomerWithPotential(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value * (10 / 100));
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.STANDARD_WITH_POTENTIAL;
    }

}