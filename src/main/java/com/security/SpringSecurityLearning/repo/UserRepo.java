package com.security.SpringSecurityLearning.repo;

import com.security.SpringSecurityLearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <User, Integer> {

    public User findByUsername(String username);
}
