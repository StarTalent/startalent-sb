package com.revenatium.startalent_sb.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revenatium.startalent_sb.accounts.Account;
import com.revenatium.startalent_sb.userRole.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles") // REVIEW: ¿Por qué no se usa el nombre 'roles'?
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

    public Role() {
    }

    public Role(String name, String description, boolean isActive, Account account) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
