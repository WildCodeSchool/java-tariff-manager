package dev.wcs.nad.tariffmanager.customer.model;

import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;

import java.time.LocalDate;

public class VICustomer extends Customer {

    public VICustomer(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value * (10d / 100d));
    }

    @Override
    public boolean isRelevantForMailing() {
        return true;
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.VI;
    }
}