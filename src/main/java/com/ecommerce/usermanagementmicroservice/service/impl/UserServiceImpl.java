package com.ecommerce.usermanagementmicroservice.service.impl;

import com.ecommerce.usermanagementmicroservice.model.enumerations.AccountStatus;
import com.ecommerce.usermanagementmicroservice.model.enumerations.Role;
import com.ecommerce.usermanagementmicroservice.model.User;
import com.ecommerce.usermanagementmicroservice.model.exceptions.InvalidUsernameOrPasswordException;
import com.ecommerce.usermanagementmicroservice.model.exceptions.PasswordsDoNotMatchException;
import com.ecommerce.usermanagementmicroservice.model.exceptions.UsernameAlreadyExistsException;
import com.ecommerce.usermanagementmicroservice.repository.UserRepository;
import com.ecommerce.usermanagementmicroservice.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }
    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String firstName,
            String lastName,
            String email,
            LocalDate dateOfBirth,
            String address,
            Long phoneNumber,
            String profilePicture,
            Role role) {
        if(username == "" || password == "" || repeatPassword == "") {
            throw new InvalidUsernameOrPasswordException();
        }
        if(!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        if(this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username,passwordEncoder.encode(password),email,firstName,lastName,dateOfBirth,address,phoneNumber,role,profilePicture,LocalDateTime.now(), AccountStatus.active);
        return userRepository.save(user);
    }

    @Override
    public User register(User user) {
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new InvalidUsernameOrPasswordException();
        }
        if(this.userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }
        User u = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()),user.getEmail(),user.getFirstName(),user.getLastName(),user.getDateOfBirth(),user.getAddress(),user.getPhoneNumber(),user.getRole(),user.getProfilePicture(),LocalDateTime.now(),AccountStatus.active);
        return userRepository.save(u);
    }
}



