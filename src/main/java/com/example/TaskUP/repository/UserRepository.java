package com.example.TaskUP.repository;

import com.example.TaskUP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
