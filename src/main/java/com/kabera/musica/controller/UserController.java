package com.kabera.musica.controller;

import com.kabera.musica.model.User;
import com.kabera.musica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/create-account")
    public String getSignUp(){
        return "create";
    }
    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestParam("email") String email,
                                           @RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           @RequestParam("role") String role){
        User user = User.builder()
                .email(email)
                .username(username)
                .password(password)
                .role(role)
                .build();
        userService.saveUser(user);
        return new ResponseEntity<>("Account created!", HttpStatus.CREATED);
    }
}
