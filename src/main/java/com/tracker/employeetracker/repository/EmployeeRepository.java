package com.tracker.employeetracker.repository;

import com.tracker.employeetracker.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);

}
