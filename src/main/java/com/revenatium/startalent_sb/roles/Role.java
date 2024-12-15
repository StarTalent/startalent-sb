package com.revenatium.startalent_sb.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revenatium.startalent_sb.accounts.Account;
import com.revenatium.startalent_sb.config.tenant.TenantAbstractBaseEntity;
import com.revenatium.startalent_sb.userRole.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "roles")
@EqualsAndHashCode(exclude = {"userRoles"})
@ToString(exclude = {"userRoles"})
public class Role extends TenantAbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    @NotBlank
    private ERole name;

    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

}
