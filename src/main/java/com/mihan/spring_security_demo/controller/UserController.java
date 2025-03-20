package com.mihan.spring_security_demo.controller;


import com.mihan.spring_security_demo.model.User;
import com.mihan.spring_security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/test")
    public String test() {
        return "testing passed";
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        System.out.println(user);
        return userService.addUser(user);
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        System.out.println(user);
        return userService.loginUser(user);
    }

    @DeleteMapping("/")
    public String deleteAllUsers(){
        userService.deleteAll();
        return "all users gone poof..";
    }

}
