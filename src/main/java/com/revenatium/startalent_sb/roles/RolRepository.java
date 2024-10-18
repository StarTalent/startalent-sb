package com.revenatium.startalent_sb.roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Role, Long> {
    List<Role>  findByName(String name);
}
