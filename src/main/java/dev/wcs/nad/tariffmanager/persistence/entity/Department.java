package dev.wcs.nad.tariffmanager.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "department")
@Getter
@Setter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Set<Tariff> tariffs = new LinkedHashSet<>();

    public void addTariff(Tariff tariff) {
        this.getTariffs().add(tariff);
        tariff.setDepartment(this);
    }

}