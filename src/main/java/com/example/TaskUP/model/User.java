package com.example.TaskUP.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "creator")
    private List<Task> tasks;
}
