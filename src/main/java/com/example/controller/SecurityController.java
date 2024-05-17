package com.example.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController //nécessaire pour que ça fonctionne, être considéré comme un controller, sinon le code sécu peut être dans un autre controller
@CrossOrigin(origins = "http://127.0.0.1:5173/") //pas obligatoire pour la partie sécu
@RequestMapping("/version/")
public class SecurityController {

    private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

    @GetMapping(path = "")
    public String getVersion(){
        return "0.0.11-SNAPSHOT";
    } //later trouver une manière de récup' l'info depuis le pom

    /* -- Security config -- */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll())
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
