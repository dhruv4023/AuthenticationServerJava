package com.authserver.authserver.repositories;

import com.authserver.authserver.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    RoleModel findByRoleName(String roleName);
}
