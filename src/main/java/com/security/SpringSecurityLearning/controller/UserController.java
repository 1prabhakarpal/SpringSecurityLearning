package com.security.SpringSecurityLearning.controller;

import com.security.SpringSecurityLearning.model.User;
import com.security.SpringSecurityLearning.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/users")
    public List<User> users() {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.verify(user);
    }

}
