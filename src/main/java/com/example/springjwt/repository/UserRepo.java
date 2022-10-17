package com.example.springjwt.repository;

import com.example.springjwt.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String userName);
}
