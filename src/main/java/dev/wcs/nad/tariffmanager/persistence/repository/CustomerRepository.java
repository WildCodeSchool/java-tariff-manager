package dev.wcs.nad.tariffmanager.persistence.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByFirstnameAndLastname(String firstname,String lastname);

    Iterable<Customer> findAllByBirthdateIsBefore(LocalDate maturityDate);

    Iterable<Customer> findAllByBirthdateIsBeforeAndLastnameContainingIgnoreCase(LocalDate maturityDate, String lastname);


}