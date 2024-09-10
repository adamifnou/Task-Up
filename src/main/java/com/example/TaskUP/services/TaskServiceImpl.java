package com.example.TaskUP.services;

import com.example.TaskUP.constants.TaskStatus;
import com.example.TaskUP.dto.TaskDto;
import com.example.TaskUP.model.Task;
import com.example.TaskUP.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task saveTask(TaskDto taskToSave, Integer creatorId) {
        try {
            // check if creator exists
            if (userService.getUserById(creatorId) == null) {
                throw new RuntimeException("User not found");
            }
            //check if task is already saved
            if (taskRepository.getTaskByTitle(taskToSave.getTitle()).isPresent()) {
                throw new RuntimeException("Task already exists");
            }
            Task task = new Task();
            //find creator by id
            task.setCreator(userService.getUserById(creatorId));
            task.setDescription(taskToSave.getDescription());
            task.setStatus(TaskStatus.PENDING);
            task.setTitle(taskToSave.getTitle());
            task.setDueDateTime(taskToSave.getDueDateTime());
            task.setCreationDateTime(LocalDateTime.now());
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException("Task not saved: "+e.getMessage());
        }
    }

    @Override
    public Task updateTask(TaskDto taskToUpdate,Integer taskId, Integer creatorId) {
        try {
            // check if task exists
            if (taskRepository.getTaskById(taskId).isEmpty()) {
                throw new RuntimeException("Task not found");
            } else if(taskRepository.getTaskByTitle(taskToUpdate.getTitle()).isPresent()) {
                if (!(taskRepository.getTaskByTitle(taskToUpdate.getTitle()).get().getCreator().getId()==creatorId)) {

                    //check if the task belongs to the creator
                    throw new RuntimeException("Task does not belong to the creator");
                }
            }
            Task task = taskRepository.getTaskByTitle(taskToUpdate.getTitle()).get();
            task.setDescription(taskToUpdate.getDescription());
            task.setTitle(taskToUpdate.getTitle());
            task.setDueDateTime(taskToUpdate.getDueDateTime());
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException("Task not updated: "+e.getMessage());
        }
    }

    @Override
    public Optional<Task> getTaskByTitle(String title) {
        // check if task exists
        try {
            Optional<Task> task = taskRepository.getTaskByTitle(title);
            if (task.isEmpty()) {
                throw new RuntimeException("No task titled: "+title+" found");
            }
            return task;
        } catch (Exception e) {
            throw new RuntimeException("Task not found");
        }
    }

    @Override
    public Optional<Task> getTaskById(Integer id) {
        // check if task exists
        try {
            Optional<Task> task = taskRepository.getTaskById(id);
            if (task.isEmpty()) {
                throw new RuntimeException("No task with id: "+id+" found");
            }
            return task;
        } catch (Exception e) {
            throw new RuntimeException("Task not found");
        }
    }

    @Override
    public List<Task> getTaskByCreatorId(Integer creatorId) {
        // check if task exists
        try {
            List<Task> tasks = taskRepository.findAllByCreatorId(creatorId);
            if (tasks.isEmpty()) {
                throw new RuntimeException("No task with creator id: "+creatorId+" found");
            }
            return tasks;
        } catch (Exception e) {
            throw new RuntimeException("Task not found: "+e.getMessage());
        }
    }
    @Override
    public void deleteTaskById(Integer id, Integer creatorId) {
        // check if task exists
        try {
            if (taskRepository.getTaskById(id).isEmpty()) {
                throw new RuntimeException("No task with id: "+id+" found");
            }
            if(!(taskRepository.getTaskById(id).get().getCreator().getId()==creatorId)){
                throw new RuntimeException("Task does not belong to the creator");
            }
            taskRepository.deleteTaskById(id);
        } catch (Exception e) {
            throw new RuntimeException("Task not deleted: "+e.getMessage());
        }
        }
}
