package com.example.TaskUP.services;

import com.example.TaskUP.constants.TaskStatus;
import com.example.TaskUP.dto.UserDto;
import com.example.TaskUP.dto.UserGetDto;
import com.example.TaskUP.model.User;
import com.example.TaskUP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDto userToSave) {
        User user = new User();
        // check User already exists
        if (alreadyExists(userToSave.getUsername())) {
            throw new RuntimeException("User already exists");
        }
        user.setUsername(userToSave.getUsername());
        user.setAge(userToSave.getAge());
        user.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        return userRepository.save(user);
    }
    @Override
    public User getUserById(Integer id) {
        // check if user exists
        // if not raise an exception
       try {
           User user = userRepository.findById(id).orElse(null);
              if (user == null) {
                throw new RuntimeException("User not found");
              }
                return user;
         } catch (Exception e) {
           throw new RuntimeException("User not found");
       }
    }

    @Override
    public boolean alreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
    @Override
    public List<UserGetDto> findAll() {
        // return all users if there are any
        // else raise an exception
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
       List<UserGetDto> userToReturn = new ArrayList<>();
        for (User user : users){
            UserGetDto userGetDto = new UserGetDto();
            userGetDto.setId(user.getId());
            userGetDto.setUsername(user.getUsername());
            userGetDto.setAge(user.getAge());
            userToReturn.add(userGetDto);
        }

        return userToReturn;
    }
    @Override
    public void deleteUser(Integer id) {
        // check if user exists
        // if not raise an exception
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
    @Override
    public List<UserGetDto> getCompletedUsers() {
        // return all users with no pending and in progress tasks
        // if there are none raise an exception
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        List<UserGetDto> userToReturn = new ArrayList<>();
        for (User user : users){
            if (user.getTasks().stream().allMatch(task -> task.getStatus().equals(TaskStatus.COMPLETED))){
                UserGetDto userGetDto = new UserGetDto();
                userGetDto.setId(user.getId());
                userGetDto.setUsername(user.getUsername());
                userGetDto.setAge(user.getAge());
                userToReturn.add(userGetDto);
            }
        }
        return userToReturn;
    }
    @Override
    public List<UserGetDto> getNotCompletedUsers() {
        // return all users with pending and in-process tasks
        // if there are none raise an exception
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        List<UserGetDto> userToReturn = new ArrayList<>();
        for (User user : users){
            if (user.getTasks().stream().anyMatch(task -> !task.getStatus().equals(TaskStatus.COMPLETED))){
                UserGetDto userGetDto = new UserGetDto();
                userGetDto.setId(user.getId());
                userGetDto.setUsername(user.getUsername());
                userGetDto.setAge(user.getAge());
                userToReturn.add(userGetDto);
            }
        }
        return userToReturn;
    }
}
