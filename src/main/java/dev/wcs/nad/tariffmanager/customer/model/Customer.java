package dev.wcs.nad.tariffmanager.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

// This class is abstract and cannot be instantiated
@Data
@AllArgsConstructor
public abstract class Customer {

    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private LocalDate lastPurchase;

    // This method is abstract and MUST NOT be implemented here - therefore the missing body.
    // This method MUST be implemented by non-abstract subclasses of this super class.
    public abstract double calculateDiscountedPrice(int value);

    // This method is specified for all subclasses of this super class.
    // If this method is not overridden, it valid on all subclasses as specified here.
    public String whoAmI() {
        return "Hi there, I am a " + this.getClass().getName() + " and my name is '" + getName() + "'";
    }

    // This method is set to false as default. If specific subclasses want to change the behaviour, they can explicitly override this method (see VICustomer).
    public boolean isRelevantForMailing() {
        return false;
    }

    /*
     * Constructor - Not necessary due to Lombok @Data
     *

    public Customer(String id, String name, String email, LocalDate birthDate, LocalDate lastPurchase) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.lastPurchase = lastPurchase;
    }

    /*
     * Getters and Setters - Not necessary due to Lombok @Data
     *

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

    /*
     * ToString
     *
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", lastPurchase=" + lastPurchase +
                '}';
    }

    /*
     * equals
     *
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    /*
     * hashCode
     *
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

     */

}
