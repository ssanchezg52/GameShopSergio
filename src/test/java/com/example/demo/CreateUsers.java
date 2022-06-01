package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.model.users.AppUser;
import com.example.demo.services.AppUserService;

@SpringBootTest
class CreateUsers {
	
	@Autowired
	AppUserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
		
	@Test
	void contextLoads() {
		AppUser admin = new AppUser("Sergio", passwordEncoder.encode("123456"));
		admin.addRole("ADMIN");
		AppUser user = new AppUser("Luis", passwordEncoder.encode("123456"));
		user.addRole("USER");
		userService.registerNewUser(admin, "123456");
		userService.registerNewUser(user, "123456");
	}
}
