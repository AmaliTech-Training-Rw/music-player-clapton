package com.kabera.musica.service;

import com.kabera.musica.model.User;
import com.kabera.musica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String saveUser(User user){
        userRepository.save(user);
        return "User saved successfully!";
    }
}
