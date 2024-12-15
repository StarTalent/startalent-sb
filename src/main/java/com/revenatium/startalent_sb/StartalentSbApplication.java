package com.revenatium.startalent_sb;

import com.revenatium.startalent_sb.config.tenant.TenantContext;
import com.revenatium.startalent_sb.roles.ERole;
import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.roles.RoleRepository;
import com.revenatium.startalent_sb.userRole.UserRole;
import com.revenatium.startalent_sb.userRole.UserRoleRepository;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.users.UserRepository;
import com.revenatium.startalent_sb.utils.RegisterUserCommand;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class StartalentSbApplication {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StartalentSbApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StartalentSbApplication.class, args);
        Environment env = context.getEnvironment();
        // Se imprimen las variables de entorno definidas en el archivo .env
        log.debug("DB_URL: {}", env.getProperty("DB_URL"));
        log.debug("DB_USERNAME: {}", env.getProperty("DB_USERNAME"));
        log.debug("DB_PASSWORD: {}", env.getProperty("DB_PASSWORD"));
	}


    @Profile("dev")
    @Bean
    CommandLineRunner init(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        return args -> {
            TenantContext.setTenantId("startalent");
            Role role = createRoleIfNotExists(roleRepository);
            RegisterUserCommand registerUserCommand = new RegisterUserCommand(
                passwordEncoder,
                userRepository,
                userRoleRepository,
                role
            );
            registerUserIfNotExists(registerUserCommand, "lgzarturo@gmail.com", "Alberto@gmail.com");
        };
    }

    static Role createRoleIfNotExists(RoleRepository roleRepository) {
        Optional<Role> savedRole = roleRepository.findByName(ERole.ADMIN);
        if (savedRole.isPresent()) {
            return savedRole.get();
        }
        Role role = Role.builder()
            .name(ERole.ADMIN)
            .build();

        return roleRepository.save(role);
    }

    static void registerUserIfNotExists(RegisterUserCommand command, String... emails) {
        for (String email : emails) {
            log.debug("User with email: {}", email);
            Optional<User> user = command.userRepository().findByEmail(email);
            if (user.isPresent()) {
                continue;
            }
            if (email == null || email.isEmpty()) {
                continue;
            }
            if (!email.contains("@")) {
                log.error("Invalid email: {}", email);
                continue;
            }
            String[] items = email.split("@");
            User newUser = User.builder()
                .email(email)
                .passwordHash(command.passwordEncoder().encode("1234"))
                .firstName(items[0])
                .lastName(items[1])
                .isActive(true)
                .build();

            User userSaved = command.userRepository().save(newUser);

            UserRole userRole = UserRole.builder()
                .user(userSaved)  // Relación con el usuario
                .role(command.role())  // Relación con el rol
                .isActive(true)
                .build();

            UserRole userRoleSaved = command.userRoleRepository().save(userRole);

            userSaved.setUserRoles(Set.of(userRoleSaved));

            command.userRepository().save(userSaved);
        }
    }
}
