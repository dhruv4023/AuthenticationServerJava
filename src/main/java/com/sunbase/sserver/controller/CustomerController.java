package com.sunbase.sserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sunbase.sserver.models.AuthRequest;
import com.sunbase.sserver.repository.CustomerService;
import com.sunbase.sserver.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.sunbase.sserver.models.Customer;

@RestController
@RequestMapping("/sunbase/portal/api/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("assignment")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        System.out.println(customer);
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully Created");
    }

    @GetMapping("assignment")
    public ResponseEntity<List<Customer>> getCustomerList() {
        List<Customer> customers = customerService.getCustomerList();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("assignment/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        System.out.printf(id);
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("assignment/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer updatedCustomer) {

        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("assignment_auth")
    public Map<String, String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        Map<String, String> res=new HashMap<>();
        if (authentication.isAuthenticated()) {
            String token= jwtService.generateToken(authRequest.getUsername());
            res.put("token",token);
            return res;
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
