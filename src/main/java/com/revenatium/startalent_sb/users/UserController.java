package com.revenatium.startalent_sb.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @GetMapping("/users")
    public String listAll() {
        return "Hello World";
    }

}
