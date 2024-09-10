package com.example.TaskUP.controllers;

import com.example.TaskUP.dto.UserDto;
import com.example.TaskUP.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // save user
    @PostMapping("/add")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userDto) {
        try {
            userService.saveUser(userDto);
            return new ResponseEntity<>(
                    userDto,
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    // get all users
    @GetMapping("all")
    public ResponseEntity<Object> getAllUsers(){
        try {
            return new ResponseEntity<>(
                    userService.findAll(),
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    // Delete user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id){
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(
                    "User deleted",
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    // get users with no pending and in progress tasks
    @GetMapping("/completed")
    public ResponseEntity<Object> getCompletedUsers(){
        try {
            return new ResponseEntity<>(
                    userService.getCompletedUsers(),
                    org.springframework.http.HttpStatus.OK
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // get users with pending and in-process tasks
    @GetMapping("/not-completed")
    public ResponseEntity<Object> getNotCompletedUsers(){
        try {
            return new ResponseEntity<>(
                    userService.getNotCompletedUsers(),
                    org.springframework.http.HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
