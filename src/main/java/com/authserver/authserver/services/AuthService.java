package com.authserver.authserver.services;

import com.authserver.authserver.models.RoleModel;
import com.authserver.authserver.models.UserModel;
import com.authserver.authserver.repositories.RoleRepository;
import com.authserver.authserver.repositories.UserRepository;
import com.authserver.authserver.security.JwtService;

import jakarta.servlet.http.HttpSession;
import com.authserver.authserver.helper.exceptions.*;
import com.authserver.authserver.helper.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtUtil;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public ResponseObject signup(UserModel user) {
        try {
            if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
                throw new ValidationException("Username, password, and email are required.");
            }

            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
            }

            // Check if role is provided
            if (user.getRole() != null && user.getRole().getRoleName() != null) {
                RoleModel role = roleRepository.findByRoleName(user.getRole().getRoleName());
                if (role == null) {
                    throw new ValidationException("Role " + user.getRole().getRoleName() + " does not exist.");
                }
                user.setRole(role);
            } else {
                // Set default role if none is provided
                RoleModel defaultRole = roleRepository.findByRoleName("USER"); // or "DEFAULT_ROLE"
                if (defaultRole == null) {
                    throw new ValidationException("Default role USER does not exist.");
                }
                user.setRole(defaultRole);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserModel createdUser = userRepository.save(user);
            return new ResponseObject(true, "User registered successfully", createdUser);
        } catch (Exception e) {
            return new ResponseObject(false, e.getMessage(), null);
        }
    }

    public ResponseObject login(String username, String password) {
        try {
            UserModel user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(null); // Remove password for security
                session.setAttribute("user", username);
                String token = jwtUtil.generateToken(username, user.getRole().getRoleName());
                return new ResponseObject(true, "Login successful", new HashMap<>() {
                    {
                        put("user", user);
                        put("token", token);
                    }
                });
            } else {
                throw new InvalidPasswordException("Invalid password for username " + username);
            }
        } catch (Exception e) {
            return new ResponseObject(false, e.getMessage(), null);
        }
    }
}
