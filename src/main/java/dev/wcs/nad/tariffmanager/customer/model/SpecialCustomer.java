package dev.wcs.nad.tariffmanager.customer.model;

import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;

import java.time.LocalDate;

public class SpecialCustomer extends Customer {

    public SpecialCustomer(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value * (5d / 100d));
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.SPECIAL;
    }

}