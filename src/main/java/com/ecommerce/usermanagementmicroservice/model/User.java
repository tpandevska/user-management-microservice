package com.ecommerce.usermanagementmicroservice.model;

import com.ecommerce.usermanagementmicroservice.model.enumerations.AccountStatus;
import com.ecommerce.usermanagementmicroservice.model.enumerations.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String address;
    private Long phoneNumber;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    private String profilePicture;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registrationDate;

    private LocalDateTime lastLoginDate;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    //Social Media Integration

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    public User() {
    }

    public User(String username, String password, String email, String firstName, String lastName, LocalDate dateOfBirth, String address, Long phoneNumber, Role role, String profilePicture, LocalDateTime lastLoginDate, AccountStatus accountStatus) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.profilePicture = profilePicture;
        this.lastLoginDate = lastLoginDate;
        this.accountStatus = accountStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
