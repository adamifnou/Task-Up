package com.example.TaskUP.repository;

import com.example.TaskUP.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByBadgeNumber(String badgeNumber);
}
