package com.revenatium.startalent_sb.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revenatium.startalent_sb.accounts.Account;
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
@EqualsAndHashCode(exclude = {"userRoles", "account"})
@ToString(exclude = {"userRoles", "account"})
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private ERole name;

    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @BatchSize(size = 20)
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @JsonIgnore
    private Account account;

}
