package com.mihan.spring_security_demo.service;

import com.mihan.spring_security_demo.model.User;
import com.mihan.spring_security_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public User addUser(User user) {

        System.out.println(user.getPassword());
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return   userRepo.save(user);
    }

    public void deleteAll() {
        userRepo.deleteAll();
    }

    public String loginUser(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                System.out.println("Login successful!");
                return jwtService.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return "Invalid credentials. Please try again.";
        }
        return "Unexpected error.";
    }

}
