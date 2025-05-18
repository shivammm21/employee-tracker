package com.tracker.employeetracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalDateTime;

@Document(collection = "locations")
@Data
public class Location {

    @Id
    private String id;

    private String employeeId;

    private double latitude;
    private double longitude;

    private LocalDateTime timestamp;

    // Getters and Setters
}
