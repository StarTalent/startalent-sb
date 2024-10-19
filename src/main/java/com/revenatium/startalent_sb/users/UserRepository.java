package com.revenatium.startalent_sb.users;

// REVIEW: Sugerencias de mejora:
//  1. Eliminar importaciones innecesarias.
import com.revenatium.startalent_sb.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
    @Query("""
        SELECT u
        FROM User u
        LEFT JOIN FETCH u.userRoles ur
        LEFT JOIN FETCH ur.role
        WHERE u.id = :id
    """)
    Optional<User> findByIdWithRoles(@Param("id") Long id);
}
