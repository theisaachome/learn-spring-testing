package com.isaachome.repository;


import com.isaachome.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Optional<Employee> findByMail(String email);
}
