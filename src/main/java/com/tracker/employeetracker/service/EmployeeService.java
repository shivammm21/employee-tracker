package com.tracker.employeetracker.service;

import com.tracker.employeetracker.entity.Employee;
import com.tracker.employeetracker.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean isEmailRegistered(String email) {
        if(employeeRepository.findByEmail(email).isPresent()){
            log.info("Email already registered");
            return true;
        }
        log.info("Email not registered");
        return false;
    }
    public Employee saveEmployee(Employee employee) {

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        log.info("User saved successfully as " + employee.getEmail());
        return employeeRepository.save(employee);
    }
    public Optional<Employee> getUserData(String email) {
        return employeeRepository.findByEmail(email);
    }

}
