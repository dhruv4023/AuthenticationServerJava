package com.authserver.authserver.controllers;

import com.authserver.authserver.helper.ResponseObject;
import com.authserver.authserver.models.UserModel;
import com.authserver.authserver.services.AuthService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService userService;

    
    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> signup(@RequestBody UserModel user) {
        ResponseObject response = userService.signup(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody Map<String, String> req) {
        ResponseObject response = userService.login(req.get("username"), req.get("password"));
        return ResponseEntity.ok(response);
    }
}
