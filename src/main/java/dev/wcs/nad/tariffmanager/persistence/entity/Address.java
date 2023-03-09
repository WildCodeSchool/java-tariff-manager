package dev.wcs.nad.tariffmanager.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String city;
    private String postalcode;
    private String street;
    private String number;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

}