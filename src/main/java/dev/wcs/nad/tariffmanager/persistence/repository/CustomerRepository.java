package dev.wcs.nad.tariffmanager.persistence.repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Iterable<Customer> findAllByBirthdateIsBefore(LocalDate maturityDate);

    Iterable<Customer> findAllByBirthdateIsBeforeAndLastnameContainingIgnoreCase(LocalDate maturityDate, String lastname);


}