package dev.wcs.nad.tariffmanager.identity.user;


import dev.wcs.nad.tariffmanager.identity.entity.UserValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface UserValidationRepository extends JpaRepository<UserValidation, Long> {
    Optional<UserValidation> findByToken(String token);
}
