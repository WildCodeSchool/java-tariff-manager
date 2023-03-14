package dev.wcs.nad.tariffmanager.persistence.entity;

import dev.wcs.nad.tariffmanager.customer.model.shared.CustomerType;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
// With @Builder, we need NoArgs and AllArgs constructor. Do not use @Data with JPA!
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String passportNo;
    private boolean blocked;
    private CustomerType customerType;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @Builder.Default
    private List<Contract> contracts = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    public void addContract(Contract contract) {
        this.getContracts().add(contract);
        contract.setCustomer(this);
    }

}