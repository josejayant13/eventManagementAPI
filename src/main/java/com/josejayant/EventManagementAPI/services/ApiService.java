package com.josejayant.EventManagementAPI.services;

import com.josejayant.EventManagementAPI.model.User;
import com.josejayant.EventManagementAPI.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepository userRepo;

    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println("Password encoded successfully");
        User savedUser = userRepo.save(user);
        return savedUser;
    }

}
