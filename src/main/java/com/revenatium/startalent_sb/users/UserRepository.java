package com.revenatium.startalent_sb.users;

import com.revenatium.startalent_sb.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstName(String firstName);
}
