package dev.wcs.nad.tariffmanager.identity.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.wcs.nad.tariffmanager.identity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByUsernameStartingWithIgnoreCase(String prefix);

}
