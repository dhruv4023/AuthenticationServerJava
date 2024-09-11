package com.authserver.authserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authserver.authserver.helper.ResponseObject;

import jakarta.servlet.http.HttpSession;

@RestController
public class MainController {

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public ResponseEntity<Object> home() {
        ResponseObject responseObject = new ResponseObject(true, "Server is running...", null);
        return ResponseEntity.ok(responseObject);
    }
}
