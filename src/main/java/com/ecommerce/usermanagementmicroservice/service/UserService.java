package com.ecommerce.usermanagementmicroservice.service;

import com.ecommerce.usermanagementmicroservice.model.enumerations.Role;
import com.ecommerce.usermanagementmicroservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String firstName, String lastName, String email, LocalDate dateOfBirth, String address, Long phoneNumber, String profilePicture, Role role);

    User register(User user);
}

