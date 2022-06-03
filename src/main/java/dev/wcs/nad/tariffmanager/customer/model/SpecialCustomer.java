package dev.wcs.nad.tariffmanager.customer.model;

import java.time.LocalDate;

public class SpecialCustomer extends Customer {

    public SpecialCustomer(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value;
    }

}