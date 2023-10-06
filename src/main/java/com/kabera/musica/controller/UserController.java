package com.kabera.musica.controller;

import com.kabera.musica.model.User;
import com.kabera.musica.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/create-account")
    public String getSignUp(){
        return "create";
    }
    @PostMapping("/signup")
    public ResponseEntity<String> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>("Account created!", HttpStatus.CREATED);
    }
}
