package com.example.TaskUP.repository;

import com.example.TaskUP.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task save(Task taskToSave);
    Optional<Task> getTaskByTitle(String title);
    Optional<Task> getTaskById(Integer id);
    List<Task> findAllByCreatorId(Integer creatorId);
    void deleteTaskById(Integer id);
}
