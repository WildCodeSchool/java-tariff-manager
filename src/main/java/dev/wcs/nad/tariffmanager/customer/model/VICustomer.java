package dev.wcs.nad.tariffmanager.customer.model;

import java.time.LocalDate;

public class VICustomer extends Customer {

    public VICustomer(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value / 10);
    }

    @Override
    public boolean isRelevantForMailing() {
        return true;
    }
}