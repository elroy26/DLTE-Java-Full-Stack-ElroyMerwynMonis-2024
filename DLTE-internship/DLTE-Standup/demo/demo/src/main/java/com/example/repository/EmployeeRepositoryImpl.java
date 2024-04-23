package com.example.repository;

import com.example.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public List<Employee> getAllEmployees() {
        // Simulated data retrieval
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John Doe"));
        employees.add(new Employee(2L, "Jane Smith"));
        return employees;
    }
}
