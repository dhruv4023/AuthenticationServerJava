package com.sunbase.sserver.controller;
import com.sunbase.sserver.models.Users;
import com.sunbase.sserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Endpoint to add a new user
    @PostMapping("/add")
    public Users addUser(@RequestBody Users user) {
        // You can add validation logic here if needed
        return userRepository.save(user);
    }
}

