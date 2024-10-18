package com.revenatium.startalent_sb.roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// REVIEW: Sugerencias de mejora:
//  1. Considerar cambiar el nombre de 'RolRepository' a 'RoleRepository' para mantener consistencia con el entidad.
//  2. Agregar un método para buscar roles por account_id, ya que según el diagrama ER, los roles podrían estar asociados a una cuenta específica.
//  3. Considerar agregar un método findByNameAndAccountId para buscar roles específicos de una cuenta.

public interface RolRepository extends JpaRepository<Role, Long> {
    List<Role>  findByName(String name);
}
