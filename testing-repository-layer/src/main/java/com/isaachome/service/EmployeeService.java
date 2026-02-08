package com.isaachome.service;

import com.isaachome.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee>  getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Employee employee, Long id);
    void deleteEmployee(Long id);
}
