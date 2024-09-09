package com.example.TaskUP.services;

import com.example.TaskUP.dto.UserDto;
import com.example.TaskUP.model.User;

public interface UserService {
    User saveUser(UserDto userToSave);
    boolean alreadyExists(String username);
}
