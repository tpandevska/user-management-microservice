package com.ecommerce.usermanagementmicroservice.web.rest;
import com.ecommerce.usermanagementmicroservice.model.User;
import com.ecommerce.usermanagementmicroservice.model.exceptions.InvalidUsernameOrPasswordException;
import com.ecommerce.usermanagementmicroservice.model.exceptions.UsernameAlreadyExistsException;
import com.ecommerce.usermanagementmicroservice.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    private final UserServiceImpl userService;

    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        try{
            this.userService.register(user);
            return "user added";
        } catch (InvalidUsernameOrPasswordException | UsernameAlreadyExistsException exception) {
            return exception.getMessage();
        }
    }
}
