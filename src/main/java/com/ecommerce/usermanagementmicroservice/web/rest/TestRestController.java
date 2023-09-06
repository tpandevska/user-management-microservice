package com.ecommerce.usermanagementmicroservice.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @RequestMapping("/hello")
    @PreAuthorize ("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello");
    }

    @RequestMapping("/all")
    public ResponseEntity<String> all() {
        return ResponseEntity.ok("Public page");
    }
}
