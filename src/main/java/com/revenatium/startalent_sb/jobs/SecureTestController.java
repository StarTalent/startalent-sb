package com.revenatium.startalent_sb.jobs;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureTestController {

    @GetMapping("/jobs")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/jobsAccessAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAll() {

        return "Hello World Admin";

    }

    @GetMapping("/jobsAccessUser")
    @PreAuthorize("hasRole('USER')")
    public String listAllSecured() {

        return "Hello World User";

    }

    @GetMapping("/jobsAccessAdminOrUser")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String listAllSecuredRoles() {

        return "Hello World Admin o User";

    }

    @GetMapping("/jobsAccessAdminT")
    @PreAuthorize("hasRole('ADMIN')")
    public String listAllSecuredRole() {

        return "Hello World Admin o User";

    }
}
