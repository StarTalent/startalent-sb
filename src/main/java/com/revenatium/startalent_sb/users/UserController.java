package com.revenatium.startalent_sb.users;

import com.revenatium.startalent_sb.exceptions.UnauthorizedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserProfileResponse> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        User user = userService.registerUser(request);
        UserProfileResponse userProfileResponse = userService.mapToProfileResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userProfileResponse);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileResponse> getUserProfile(
        @PathVariable Long userId,
        @RequestParam(defaultValue = "false") boolean impersonate) {

        boolean isAdmin = SecurityContextHolder.getContext()
            .getAuthentication()
            .getAuthorities()
            .stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (impersonate && !isAdmin) {
            throw new UnauthorizedException("Solo los administradores pueden realizar el impersonación");
        }

        UserProfileResponse profile = userService.getUserProfile(userId, isAdmin);
        return ResponseEntity.ok(profile);
    }

}
