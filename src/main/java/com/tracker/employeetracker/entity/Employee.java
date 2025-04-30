package com.tracker.employeetracker.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(nullable = false)
    private String role; // "ADMIN" or "EMPLOYEE"

    @Column(name = "work_start_time")
    private LocalTime workStartTime;

    @Column(name = "work_end_time")
    private LocalTime workEndTime;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Location> locations;

    // Getters and Setters
}

