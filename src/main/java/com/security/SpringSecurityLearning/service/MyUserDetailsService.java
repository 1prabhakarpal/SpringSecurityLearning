package com.security.SpringSecurityLearning.service;

import com.security.SpringSecurityLearning.model.User;
import com.security.SpringSecurityLearning.model.UserPrinciple;
import com.security.SpringSecurityLearning.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        log.info("User: "+user);
        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }


        return new UserPrinciple(user);
    }
}
