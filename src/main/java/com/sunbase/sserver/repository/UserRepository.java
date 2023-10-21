package com.sunbase.sserver.repository;
import com.sunbase.sserver.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    // You can add custom queries here if needed
}
