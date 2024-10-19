package com.revenatium.startalent_sb.users;

import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.userRole.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Nested
    @DisplayName("buscar por nombre")
    class FindByFirstNameTests {
        @Test
        @DisplayName("debería devolver usuarios cuando el nombre existe")
        void shouldReturnUsers_WhenFirstNameExists() {
            // given: crear un usuario con el nombre "John" y guardarlo en la base de datos
            User user = new User();
            user.setFirstName("John");
            userRepository.save(user);

            // when: ejecutar la prueba
            List<User> result = userRepository.findByFirstName("John");

            // then: verificar el resultado
            assertThat(result).isNotEmpty();
            assertThat(result.getFirst().getFirstName()).isEqualTo("John");
        }

        @Test
        @DisplayName("debería devolver una lista vacía cuando el nombre no existe")
        void shouldReturnEmptyList_WhenFirstNameDoesNotExist() {
            // given: no se necesita preparación de datos en este caso

            // when: ejecutar la prueba
            List<User> result = userRepository.findByFirstName("NonExistent");

            // then: verificar el resultado
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("debería devolver una lista vacía cuando el nombre es nulo")
        void shouldReturnEmptyList_WhenFirstNameIsNull() {
            // given: no se necesita preparación de datos en este caso

            // when: ejecutar la prueba
            List<User> result = userRepository.findByFirstName(null);

            // then: verificar el resultado
            assertThat(result).isEmpty();
        }
    }


    @Nested
    @DisplayName("buscar por id con roles")
    class FindByIdWithRolesTests {
        @Test
        @DisplayName("debería devolver usuario con roles cuando el id existe")
        void shouldReturnUserWithRoles_WhenIdExists() {
            // given: crear un usuario real y guardarlo en la base de datos
            User user = new User();
            user.setId(1L);
            user.setFirstName("John");
            user.setLastName("Doe");

            Role role = new Role();
            role.setId(1L);
            role.setName("ADMIN");

            UserRole userRole = new UserRole(user, role);
            user.setUserRoles(Set.of(userRole));

            userRepository.save(user);

            // when: ejecutar la prueba
            Optional<User> result = userRepository.findByIdWithRoles(1L);

            // then: verificar el resultado
            assertThat(result).isPresent();
            assertThat(result.get().getUserRoles()).isNotEmpty();
        }

        @Test
        @DisplayName("debería devolver vacío cuando el id no existe")
        void shouldReturnEmpty_WhenIdDoesNotExist() {
            // given: no se necesita preparación de datos en este caso

            // when: ejecutar la prueba
            Optional<User> result = userRepository.findByIdWithRoles(999L);

            // then: verificar el resultado
            assertThat(result).isEmpty();
        }

        @Test
        @DisplayName("debería devolver vacío cuando el id es nulo")
        void shouldReturnEmpty_WhenIdIsNull() {
            // given: no se necesita preparación de datos en este caso

            // when: ejecutar la prueba
            Optional<User> result = userRepository.findByIdWithRoles(null);

            // then: verificar el resultado
            assertThat(result).isEmpty();
        }
    }
}
