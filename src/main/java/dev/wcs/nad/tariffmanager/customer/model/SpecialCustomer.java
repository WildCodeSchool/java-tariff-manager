package dev.wcs.nad.tariffmanager.customer.model;

import java.time.LocalDate;

public class SpecialCustomer extends Customer {

    private final double discount;

    public SpecialCustomer(double discount, String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
        this.discount = discount;
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value * (discount / 100));
    }

}