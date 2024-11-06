package com.revenatium.startalent_sb.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.revenatium.startalent_sb.jobs.Job;
import com.revenatium.startalent_sb.roles.Role;
import com.revenatium.startalent_sb.users.User;
import com.revenatium.startalent_sb.utils.JsonNodeConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts") // REVIEW: ¿Por qué no se usa el nombre 'accounts'?
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(exclude = {"users", "jobs", "roles"})
@ToString(exclude = {"users", "jobs", "roles"})
public class Account {

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
    @JsonIgnore
    private List<Role> roles;

}

