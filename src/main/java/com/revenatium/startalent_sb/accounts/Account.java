package com.revenatium.startalent_sb.accounts;

import com.fasterxml.jackson.databind.JsonNode;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.utils.JsonNodeConverter;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "account") // REVIEW: ¿Por qué no se usa el nombre 'accounts'?
public class Account {

    // REVIEW: Sugerencias de mejora:
    //  1. Agregar campos para fechas de creación y actualización (createdAt, updatedAt).
    //  2. Considerar agregar un campo para el plan o tipo de cuenta (por ejemplo, 'accountType' o 'plan').
    //  3. Agregar una relación con Job para manejar las vacantes asociadas a la cuenta.
    //  4. Implementar métodos equals() y hashCode() para comparaciones adecuadas.
    //  5. Agregar validaciones con anotaciones de Jakarta Bean Validation (por ejemplo, @NotBlank para 'name' y 'domain').
    //  6. Considerar usar @JsonIgnore en la propiedad 'users' para evitar serialización circular en caso de usar la entidad en respuestas REST.
    //  7. Considerar que al usar @Column(name = "isactive") se puede omitir el nombre de la columna si coincide con el nombre del atributo.
    //      7.1. Un mejor acercamiento sería usar @Column(name = "is_active") para seguir la convención de nombres de columnas en snake_case.
    //      7.2. El usar snake_case en los nombres de columnas es una buena práctica porque se lee mejor y es más fácil de entender.

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String domain;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "json", name = "brandingconfig")
    private JsonNode brandingConfig;

    @Column(name = "isactive")
    private boolean isActive;

    @OneToMany(mappedBy = "account")
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public JsonNode getBrandingConfig() {
        return brandingConfig;
    }

    public void setBrandingConfig(JsonNode brandingConfig) {
        this.brandingConfig = brandingConfig;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

