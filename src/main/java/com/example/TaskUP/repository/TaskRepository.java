package com.example.TaskUP.repository;

import com.example.TaskUP.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
