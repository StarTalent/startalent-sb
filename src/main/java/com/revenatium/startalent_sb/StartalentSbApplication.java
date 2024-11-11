package com.revenatium.startalent_sb;

import com.revenatium.startalent_sb.config.tenant.TenantContext;
import com.revenatium.startalent_sb.roles.ERole;
import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.roles.RoleRepository;
import com.revenatium.startalent_sb.userRole.UserRole;
import com.revenatium.startalent_sb.userRole.UserRoleRepository;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.users.UserRepository;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

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


    @Bean
    CommandLineRunner init(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        return args -> {
            TenantContext.setTenantId("startalent");
            long numberOfUsers = userRepository.count();
            if (numberOfUsers == 0) {
                Role role = Role.builder()
                    .name(ERole.ADMIN)
                    .build();

                Role roleSaved = roleRepository.save(role);

                User user = User.builder()
                    .email("Alberto@gmail.com")
                    .passwordHash(passwordEncoder.encode("1234"))
                    .firstName("Alberto")
                    .lastName("Alvarez")
                    .isActive(true)
                    .build();

                User userSaved = userRepository.save(user);

                UserRole userRole = UserRole.builder()
                    .user(userSaved)  // Relación con el usuario
                    .role(roleSaved)  // Relación con el rol
                    .isActive(true)
                    .build();

                UserRole userRoleSaved = userRoleRepository.save(userRole);

                user.setUserRoles(Set.of(userRoleSaved));

                userRepository.save(user);
            }
        };
    }
}
