package com.isaachome.service.impl;

import com.isaachome.exception.ResourceNotFoundException;
import com.isaachome.model.Employee;
import com.isaachome.repository.EmployeeRepo;
import com.isaachome.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> savedEmployee = employeeRepo.findByMail(employee.getMail());
        if (savedEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee already exists with given mail" +  employee.getMail());
        }
        var emp = employeeRepo.save(employee);
        return emp;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return  employeeRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Employee found with given ID : " + id));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        return null;
    }

    @Override
    public void deleteEmployee(Long id) {

    }
}
