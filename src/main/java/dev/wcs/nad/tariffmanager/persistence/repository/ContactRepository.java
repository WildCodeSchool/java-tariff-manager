package dev.wcs.nad.tariffmanager.persistence.repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

}