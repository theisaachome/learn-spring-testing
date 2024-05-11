package com.isaachome.repository;


import com.isaachome.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Optional<Employee> findByMail(String email);

    @Query("SELECT e FROM Employee  e WHERE e.firstName =?1 and e.lastName =?2")
    Employee findByJPQL(String firstName,String lastName);
}
