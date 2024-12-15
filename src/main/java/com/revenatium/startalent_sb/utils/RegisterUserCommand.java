package com.revenatium.startalent_sb.utils;

import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.userRole.UserRoleRepository;
import com.revenatium.startalent_sb.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public record RegisterUserCommand(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository, Role role) {
}
