package com.revenatium.startalent_sb.userRole;

import com.revenatium.startalent_sb.accounts.Account;
import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.users.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "user_roles") // REVIEW: ¿Por qué no se usa el nombre 'user_roles'?
@EntityListeners(AuditingEntityListener.class)
public class UserRole {

    // REVIEW: Sugerencias de mejora:
    //  1. Considerar agregar una referencia a Account, ya que según el diagrama ER, las relaciones de usuario y rol podrían estar asociadas a una cuenta específica.
    //  2. Agregar un campo 'isActive' para manejar roles activos/inactivos sin eliminar el registro.
    //  3. Implementar métodos equals() y hashCode() para comparaciones adecuadas.
    //  4. Agregar anotaciones @CreatedDate y @LastModifiedDate de Spring Data para manejar automáticamente las fechas de creación y actualización.
    //  5. Agregar un método para buscar roles por user_id y role_id.

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @CreatedDate
    @Column(name = "assigned_at", updatable = false)
    private LocalDateTime assignedAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public UserRole() {
    }

    public UserRole(User user, Role role, Account account, LocalDateTime assignedAt) {
        this.user = user;
        this.role = role;
        this.account = account;
        this.assignedAt = assignedAt;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(user, userRole.user) &&
                Objects.equals(role, userRole.role) &&
                Objects.equals(account, userRole.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role, account);
    }
}
