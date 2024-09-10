package com.example.TaskUP.services;

import com.example.TaskUP.dto.TaskDto;
import com.example.TaskUP.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task saveTask(TaskDto taskToSave, Integer creatorId);
    Task updateTask(TaskDto taskToUpdate,Integer taskId, Integer creatorId);
    Optional<Task> getTaskByTitle(String title);
    Optional<Task> getTaskById(Integer id);
    List<Task> getTaskByCreatorId(Integer creatorId);
    void deleteTaskById(Integer id, Integer creatorId);
}
