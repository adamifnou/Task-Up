package com.example.TaskUP.dto;

import com.example.TaskUP.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserGetDto {
    private Integer id;
    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
