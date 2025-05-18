package com.tracker.employeetracker.service;

import com.tracker.employeetracker.entity.Employee;
import com.tracker.employeetracker.repository.EmployeeRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final EmployeeRepository employeeRepository;

    public UserDetailsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!StringUtils.hasText(username)) {
            throw new UsernameNotFoundException("Username cannot be empty");
        }

        return employeeRepository.findByEmail(username)
                .map(employee -> new User(
                        employee.getEmail(),
                        employee.getPassword(),
                        List.of(new SimpleGrantedAuthority(DEFAULT_ROLE))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
    }
}