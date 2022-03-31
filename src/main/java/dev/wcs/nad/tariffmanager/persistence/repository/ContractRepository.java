package dev.wcs.nad.tariffmanager.persistence.repository;

import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long> {

}
