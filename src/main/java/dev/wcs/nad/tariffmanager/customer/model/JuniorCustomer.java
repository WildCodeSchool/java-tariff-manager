package dev.wcs.nad.tariffmanager.customer.model;

import java.time.LocalDate;

public class JuniorCustomer extends StandardCustomer {

    private int discountPercentage;

    public JuniorCustomer(int discountPercentage, String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        super(id, name, email, birthDate, lastPurchase);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateDiscountedPrice(int value) {
        return value - (value / discountPercentage);
    }

}