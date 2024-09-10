package com.authserver.authserver.config;

import com.authserver.authserver.models.RoleModel;
import com.authserver.authserver.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public CommandLineRunner initializeRoles() {
        return args -> {
            if (roleRepository.findByRoleName("user") == null) {
                RoleModel userRole = new RoleModel();
                userRole.setRoleName("user");
                userRole.setDescription("Standard user role");
                roleRepository.save(userRole);
            }

            if (roleRepository.findByRoleName("admin") == null) {
                RoleModel adminRole = new RoleModel();
                adminRole.setRoleName("admin");
                adminRole.setDescription("Administrator role with full access");
                roleRepository.save(adminRole);
            }
        };
    }
}
