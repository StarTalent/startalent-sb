package com.revenatium.startalent_sb.candidates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "candidates")
public class Candidate extends TenantAbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @JsonProperty("profile_data")
    private String  profileData;

    private String skills;

    private String experience;
}
