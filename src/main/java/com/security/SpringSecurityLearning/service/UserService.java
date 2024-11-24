package com.security.SpringSecurityLearning.service;

import com.security.SpringSecurityLearning.model.User;
import com.security.SpringSecurityLearning.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        String tokString = authentication.isAuthenticated() ? jwtService.generateToken(user.getUsername()) : "Failure";
        System.out.println(tokString);
        return tokString;
    }
}
