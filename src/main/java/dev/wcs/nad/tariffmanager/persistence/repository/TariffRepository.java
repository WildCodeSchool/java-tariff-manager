package dev.wcs.nad.tariffmanager.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {
    Optional<Tariff> findByName(String name);
}
