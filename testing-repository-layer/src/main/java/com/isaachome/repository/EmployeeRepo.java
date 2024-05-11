package com.isaachome.repository;


import com.isaachome.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByMail(String email);

    // define custom query using JPQL with index params.
    @Query("SELECT e FROM Employee  e WHERE e.firstName =?1 and e.lastName =?2")
    Employee findByJPQL(String firstName, String lastName);

    // define custom query using JPQL with Named params.
    @Query("SELECT e FROM Employee  e WHERE  e.firstName =:firstName and e.lastName =:lastName")
    Employee findByJPQLNamesParams(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // define custom query using Native Query with Index.
    @Query(value = "SELECT * FROM employees  e WHERE e.first_name=?1 AND e.last_name=?2", nativeQuery = true)
    Employee findByNativeQueryWithIndexParams(String firstName, String lastName);


    // define custom query using Native Query with Named params.
    @Query(value = "SELECT * FROM employees  e WHERE e.first_name=:firstName AND e.last_name=:lastName", nativeQuery = true)
    Employee findByNativeQueryWithNamedParams(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
