package com.tracker.employeetracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "employees")
@Data
public class Employee {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private String role; // "MANAGER" or "EMPLOYEE"

    private LocalTime workStartTime;

    private LocalTime workEndTime;

    private LocalDateTime createdAt = LocalDateTime.now();

    private List<Location> locations = new ArrayList<>();

    // Getters and Setters
}

