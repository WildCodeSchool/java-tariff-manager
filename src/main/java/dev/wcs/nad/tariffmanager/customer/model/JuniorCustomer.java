package dev.wcs.nad.tariffmanager.customer.model;

import java.time.LocalDate;

public class JuniorCustomer extends StandardCustomer {

    private double discountPercentage;

    public JuniorCustomer(double discountPercentage, String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value * (discountPercentage / 100));
    }

}