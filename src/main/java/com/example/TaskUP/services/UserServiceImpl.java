package com.example.TaskUP.services;

import com.example.TaskUP.dto.UserDto;
import com.example.TaskUP.model.User;
import com.example.TaskUP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public boolean alreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

}
