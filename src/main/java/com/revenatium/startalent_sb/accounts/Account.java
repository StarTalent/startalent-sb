package com.revenatium.startalent_sb.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.revenatium.startalent_sb.jobs.Job;
import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.utils.JsonNodeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts") // REVIEW: ¿Por qué no se usa el nombre 'accounts'?
@EntityListeners(AuditingEntityListener.class)
public class Account {

    // REVIEW: Sugerencias de mejora:
    //  1. Agregar campos para fechas de creación y actualización (createdAt, updatedAt).
    //  2. Considerar agregar un campo para el plan o tipo de cuenta (por ejemplo, 'accountType' o 'plan').
    // --> pendiente  3. Agregar una relación con Job para manejar las vacantes asociadas a la cuenta.
    //  4. Implementar métodos equals() y hashCode() para comparaciones adecuadas.
    //  5. Agregar validaciones con anotaciones de Jakarta Bean Validation (por ejemplo, @NotBlank para 'name' y 'domain').
    //  6. Considerar usar @JsonIgnore en la propiedad 'users' para evitar serialización circular en caso de usar la entidad en respuestas REST.
    //  7. Considerar que al usar @Column(name = "isactive") se puede omitir el nombre de la columna si coincide con el nombre del atributo.
    //      7.1. Un mejor acercamiento sería usar @Column(name = "is_active") para seguir la convención de nombres de columnas en snake_case.
    //      7.2. El usar snake_case en los nombres de columnas es una buena práctica porque se lee mejor y es más fácil de entender.

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String domain;

    @Convert(converter = JsonNodeConverter.class)
    @Column(columnDefinition = "json", name = "branding_config")
    private JsonNode brandingConfig;

    @Column(name = "is_active")
    private boolean isActive;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "account_type")
    private String accountType;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Job> jobs;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Role> roles;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(name, account.name) &&
                Objects.equals(domain, account.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, domain);
    }

}

