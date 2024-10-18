package com.revenatium.startalent_sb.userRole;

import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.users.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_role") // REVIEW: ¿Por qué no se usa el nombre 'user_roles'?
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    public UserRole() {
    }

    public UserRole(User user, Role role, LocalDateTime assignedAt) {
        this.user = user;
        this.role = role;
        this.assignedAt = assignedAt;
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

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }

}
