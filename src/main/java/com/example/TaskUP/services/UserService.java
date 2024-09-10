package com.example.TaskUP.services;

import com.example.TaskUP.dto.UserDto;
import com.example.TaskUP.dto.UserGetDto;
import com.example.TaskUP.model.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDto userToSave);
    User getUserById(Integer id);
    boolean alreadyExists(String username);
    List<UserGetDto> findAll();
    void deleteUser(Integer id);
    List<UserGetDto> getCompletedUsers();
    List<UserGetDto> getNotCompletedUsers();
}
