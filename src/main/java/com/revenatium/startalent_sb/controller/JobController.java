package com.revenatium.startalent_sb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobController {


    /* Lista todos los empleados */
    @GetMapping("/jobs")
    public String listAll() {

        return "Hello World";

    }
}
