package com.revenatium.startalent_sb.roles;

import com.revenatium.startalent_sb.userRole.UserRole;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role") // REVIEW: ¿Por qué no se usa el nombre 'roles'?
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @Column(name = "isactive")
    private boolean isActive;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {
    }

    public Role(String name, String description, boolean isActive) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


}
