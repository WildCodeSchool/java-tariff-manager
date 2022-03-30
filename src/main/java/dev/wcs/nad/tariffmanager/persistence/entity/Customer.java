package dev.wcs.nad.tariffmanager.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
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

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Contract> contracts = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "contact_id")
    private Contact contact;

}