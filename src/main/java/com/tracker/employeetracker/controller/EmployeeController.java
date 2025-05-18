package com.tracker.employeetracker.controller;

import com.tracker.employeetracker.entity.Employee;
import com.tracker.employeetracker.repository.EmployeeRepository;
import com.tracker.employeetracker.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {


        if (employeeService.isEmailRegistered(employee.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        Employee savedEmployee = employeeService.saveEmployee(employee);

        return ResponseEntity.ok(savedEmployee);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginEmployee(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        if (password == null || password.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        // Check credentials
        Optional<Employee> employee = employeeService.getUserData(email);
        
        if (employee.isPresent() && employee.get().getPassword().equals(password)) {
            log.info("User Authenticated by email:"+(employee.get().getEmail()));
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("role", employee.get().getRole());
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.badRequest().body("Invalid email or password");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        log.info("User fetched by id:"+(employee.get().getEmail()));
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        log.info("All employees fetched");
        return employeeRepository.findAll();
    }
}
