package com.kabera.musica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musica")
public class TestController {

    @GetMapping("/pub/home")
    public String index(){
        return "index is here home!";
    }

    @GetMapping("/artist/create")
    public String createSong(){
        return "Artist working on some jams!";
    }
}
