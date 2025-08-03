package com.isaachome.service;

import com.isaachome.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee>  getAllEmployees();
}
