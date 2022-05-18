package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.users.AppUser;
import com.example.demo.model.users.UserRepository;


@Service
public class AppUserService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser loadUserByUsername(String username){
        return userRepository.findUserByUsername(username).get();
    }

    public boolean registerNewUser(AppUser appUser,String password){
        Optional<AppUser> user = userRepository.findUserByUsername(appUser.getUsername());
        boolean isRegister = false;
        if (user.isEmpty() && passwordEncoder.matches(password, appUser.getPassword())){
            this.userRepository.save(appUser);
            isRegister = true;
        }
        return isRegister;
    }
}
