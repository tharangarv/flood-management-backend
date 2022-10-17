package com.example.springjwt.repository;

import com.example.springjwt.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepo extends JpaRepository<Alert, Long> {

    List<Alert> findTop10ByOrderByIdDesc();

    List<Alert> findByOrderByIdDesc();

}
