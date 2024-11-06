package com.revenatium.startalent_sb.roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    List<Role> findByAccountId(Long accountId);

    List<Role> findByNameAndAccountId(ERole name, Long accountId);
}
