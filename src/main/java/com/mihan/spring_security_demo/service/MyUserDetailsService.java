package com.mihan.spring_security_demo.service;

import com.mihan.spring_security_demo.model.User;
import com.mihan.spring_security_demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByUsername(username);

        if(user == null){
            System.out.println("User "+username+"404");
            throw  new UsernameNotFoundException("User "+username+"404");
        }
        return user;
    }
}
