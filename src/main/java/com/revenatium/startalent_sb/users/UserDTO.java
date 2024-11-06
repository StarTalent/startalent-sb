package com.revenatium.startalent_sb.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank

    private String passwordHash;

    @NotBlank
    private String firstName;

    @NotBlank String lastName;

    private List<String> userRoles;

}
