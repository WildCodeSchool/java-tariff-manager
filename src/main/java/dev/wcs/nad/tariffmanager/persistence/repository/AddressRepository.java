package dev.wcs.nad.tariffmanager.persistence.repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}