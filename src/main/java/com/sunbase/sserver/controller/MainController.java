package com.sunbase.sserver.controller;

import com.sunbase.sserver.models.Users;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/")
    public String home(){
        Users u= new Users("abc","abc@m.c","123");

        return "hello world";
    }
}
