package com.revenatium.startalent_sb.recruiters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revenatium.startalent_sb.config.tenant.TenantAbstractBaseEntity;
import com.revenatium.startalent_sb.users.User;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "recruiters")
public class Recruiter extends TenantAbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    private String department;

    private String position;

}
