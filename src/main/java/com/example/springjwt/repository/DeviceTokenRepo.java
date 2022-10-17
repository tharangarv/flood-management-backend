package com.example.springjwt.repository;

import com.example.springjwt.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceTokenRepo extends JpaRepository<DeviceToken, Long> {
}
