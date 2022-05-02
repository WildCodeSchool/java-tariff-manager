package dev.wcs.nad.tariffmanager.customer.model;

import java.time.LocalDate;

public abstract class Customer {

    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private LocalDate lastPurchase;

    public Customer(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.lastPurchase = lastPurchase;
    }

    public abstract double calculateDiscountedPrice(int value);

    public String whoAmI() {
        return "Hi there, I am a " + this.getClass().getName() + " and my name is '" + getName() + "'";
    }

    /*
     * Getters and Setters
     */

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(LocalDate lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

}
