package com.revenatium.startalent_sb.userRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);

}
