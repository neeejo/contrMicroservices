package it.contrader.authenticationservice.repository;

import it.contrader.authenticationservice.model.Role;
import it.contrader.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    List<User> findByRolesRole(Role.ERole role);


}
