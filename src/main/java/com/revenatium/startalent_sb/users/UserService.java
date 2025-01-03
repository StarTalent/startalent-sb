package com.revenatium.startalent_sb.users;

import com.revenatium.startalent_sb.exceptions.EmailAlreadyExistsException;
import com.revenatium.startalent_sb.exceptions.UnauthorizedException;
import com.revenatium.startalent_sb.exceptions.UserNotFoundException;
import com.revenatium.startalent_sb.roles.ERole;
import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.roles.RoleRepository;
import com.revenatium.startalent_sb.userRole.UserRole;
import com.revenatium.startalent_sb.userRole.UserRoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public User registerUser(UserRegistrationRequest request) {
        log.info("Iniciando registro de usuario con el email: {}", request.getEmail());
        try {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("El usuario ya existe");
            }

            Role userRole = roleRepository.findByName(ERole.USER).orElseThrow(() -> new EntityNotFoundException("No se encontró el rol: "+ERole.USER));

            User user = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .build();

            User savedUser = userRepository.save(user);

            UserRole roleAssignment = UserRole.builder()
                .user(savedUser)
                .role(userRole)
                .isActive(true)
                .build();

            userRoleRepository.save(roleAssignment);
            user.setUserRoles(Set.of(roleAssignment));

            log.info("Usuario registrado con éxito con el ID: {}", savedUser.getId());
            return savedUser;
        } catch (Exception e) {
            log.error("Error al registrar el usuario: {}", e.getMessage(), e);
            throw new RuntimeException("Error al registrar el usuario");
        }
    }

    @Transactional(readOnly = true)
    public UserProfileResponse getUserProfile(Long userId, boolean isAdmin) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("No se encontró el usuario"));

        if (!isAdmin && !SecurityContextHolder.getContext().getAuthentication().getName().equals(user.getEmail())) {
            throw new UnauthorizedException("Not authorized to view this profile");
        }

        return mapToProfileResponse(user);
    }

    @Transactional(readOnly = true)
    public UserProfileResponse mapToProfileResponse(User user) {
        Set<ERole> roles;
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        roles = userRoles.stream()
            .map(userRole -> userRole.getRole().getName())
            .collect(Collectors.toSet());

        return new UserProfileResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getCreatedAt(),
            roles.stream().map(ERole::name).collect(Collectors.toSet())
        );
    }
}
