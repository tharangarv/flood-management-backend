package com.example.springjwt.repository;

import com.example.springjwt.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByUserRoleName(String role);
}
