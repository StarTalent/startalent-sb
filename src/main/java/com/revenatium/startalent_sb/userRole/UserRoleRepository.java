package com.revenatium.startalent_sb.userRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

    @Query("SELECT ur FROM UserRole ur JOIN FETCH ur.role r WHERE ur.user.id = :userId")
    List<UserRole> findByUserId(Long userId);

    List<UserRole> findByRoleId(Long roleId);
}
