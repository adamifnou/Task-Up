package com.example.TaskUP.controllers;


import com.example.TaskUP.dto.TaskDto;
import com.example.TaskUP.model.Task;
import com.example.TaskUP.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public  TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveTask(@RequestParam Integer creatorId,
                                           @RequestBody TaskDto taskToSave) {
        try {
            Task savedTask = taskService.saveTask(taskToSave, creatorId);
            return new ResponseEntity<>(
                    savedTask,
                    org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateTask(@RequestParam Integer creatorId,
                                             @RequestBody TaskDto taskToUpdate,
                                             @RequestParam Integer taskId) {
        try {
            Task updatedTask = taskService.updateTask(taskToUpdate,taskId, creatorId);
            return new ResponseEntity<>(
                    updatedTask,
                    org.springframework.http.HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get-task/task-title/{title}")
    public ResponseEntity<Object> getTaskByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(
                    taskService.getTaskByTitle(title),
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get-task/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable Integer taskId) {
        try {
            return new ResponseEntity<>(
                    taskService.getTaskById(taskId),
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/get-tasks/{creatorId}")
    public ResponseEntity<Object> getTaskByCreatorId(@PathVariable Integer creatorId) {
        try {
            return new ResponseEntity<>(
                    taskService.getTaskByCreatorId(creatorId),
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
