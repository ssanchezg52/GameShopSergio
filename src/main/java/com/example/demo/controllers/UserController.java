package com.example.demo.controllers;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.model.users.AppUser;
import com.example.demo.model.users.UserDTO;
import com.example.demo.security.ApplicationUserRol;
import com.example.demo.services.AppUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public boolean registerUser(UserDTO userDTO){
        AppUser appUser = new AppUser(userDTO.getUsername(),passwordEncoder.encode(userDTO.getPassword()));
        appUser.addRole(ApplicationUserRol.GUEST.name());
        return appUserService.registerNewUser(appUser,userDTO.getPasswordConfirm());
    }

    @GetMapping("/token/refresh")
    public void getRefreshToken(HttpServletRequest request, HttpServletResponse response){
        String authorizartionHeader = request.getHeader(AUTHORIZATION);
        if (authorizartionHeader != null && authorizartionHeader.startsWith("Bearer ")) {
            String refreshToken = authorizartionHeader.substring("Bearer ".length());
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodecJWT = verifier.verify(refreshToken);
            String username = decodecJWT.getSubject();
            AppUser appUser = appUserService.loadUserByUsername(username);
            User user = appUser.parseToUser();
            String accesToken = JWT.create()
                    .withSubject(user.getUsername())
                    //TimeStamp es para pasar localDAte a DAte y digo de aqui a diez minutos
                    .withExpiresAt(Timestamp.valueOf(LocalDateTime.now().plusMinutes(30)))
                    .withIssuer(request.getRequestURL().toString())
                    .withArrayClaim("roles",
                            user
                                    .getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()).toArray(new String[0]))
                    .sign(algorithm);
            HashMap<String, String> tokens=new HashMap<>();
            tokens.put("access_token", accesToken);
            tokens.put("refresh_token", refreshToken);
            response.setContentType(APPLICATION_JSON_VALUE);
            try {
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
