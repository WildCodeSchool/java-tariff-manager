package dev.wcs.nad.tariffmanager.customer.collectiontesting;

public class Administrator {

    private String name;
    private String email;
    private boolean active;

    public Administrator(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Administrator(String name, String email, boolean active) {
        this.name = name;
        this.email = email;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String checkIfActive() {
        if (this.active) {
            return "I am " + this.name + " and I am currently active";
        } else {
            return "I am " + this.name + " and I am currently inactive";
        }
    }

}
