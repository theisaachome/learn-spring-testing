package com.isaachome.repository;

import com.isaachome.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class EmployeeRepoTest {
    @Autowired
    private EmployeeRepo employeeRepo;

    private Employee employee;

    @BeforeEach
    public void setEmployee() {
        this.employee = Employee.builder()
                .firstName("isaac")
                .lastName("home")
                .mail("isaachome@gmail.com")
                .build();
    }

    // Junit test for save Employee operation
    // public void given_When_then(){
    //   given  => pre-condition or setup
    //   When => Action or the behaviour that we are going test
    // }
    @DisplayName("Junit test for save Employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        // given -> precondition or setup
        // when -> action or the behaviour that we are going to test
        var savedEmployee = employeeRepo.save(employee);

        // then -> verify the output...
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    // JUnit test for findAll employee operation
    @Test
    public void givenEmployeeList_whenFindAll_thenEmployeeList() {
        // given -> precondition or setup
        var emp1 = Employee.builder()
                .firstName("isaac")
                .lastName("home")
                .mail("isaachome@gmail.com")
                .build();
        var emp2 = Employee.builder()
                .firstName("mercy")
                .lastName("home")
                .mail("mercyhome@gmail.com")
                .build();

        employeeRepo.saveAll(List.of(emp1, emp2));
        // when -> action or the behaviour that we are going to test
        List<Employee> employees = employeeRepo.findAll();

        // then -> verify the output...
        assertThat(employees).isNotNull();
        assertThat(employees.size()).isEqualTo(2);
    }

    // JUnit test for getEmployee By ID operation
    @Test
    public void givenId_whenFindByEmployeeID_thenEmployee() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        // when -> action or the behaviour that we are going to test
        Employee savedEmp = employeeRepo.findById(employee.getId()).get();
        // then -> verify the output...
        assertThat(savedEmp).isNotNull();
    }


    // JUnit test for get Employee by email operation
    @Test
    public void givenEmail_whenFindByEmail_thenReturnEmployeeObject() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        // when -> action or the behaviour that we are going to test
        var employeeDB = employeeRepo.findByMail(employee.getMail()).get();
        // then -> verify the output...
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getMail()).isEqualTo("isaachome@gmail.com");
    }

    // JUnit test for update Employee Operation
    @DisplayName("JUnit test for update Employee Operation")
    @Test
    public void givenEmployeeObject_whenUpdatedEmployee_thenReturnUpdatedEmployee() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        // when -> action or the behaviour that we are going to test
        Employee savedEmp = employeeRepo.findById(employee.getId()).get();
        savedEmp.setMail("mercy@gmail.com");
        savedEmp.setFirstName("mercy");
        Employee updateEmp = employeeRepo.save(savedEmp);
        // then -> verify the output...
        assertThat(updateEmp.getMail()).isEqualTo("mercy@gmail.com");
        assertThat(updateEmp.getFirstName()).isEqualTo("mercy");
    }

    // JUnit test for delete operation
    @DisplayName("JUnit test for delete operation")
    @Test
    public void givenEmployeeObject_whenDeleteEmployeeById_thenReturnNull() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        // when -> action or the behaviour that we are going to test
        employeeRepo.delete(employee);
        Optional<Employee> optionalEmployee = employeeRepo.findById(employee.getId());

        // then -> verify the output...
        assertThat(optionalEmployee).isEmpty();
    }

    // JUnit test for custom query using JPQL  with index
    @DisplayName("Junit Test for findByJPQL")
    @Test
    public void givenEmployeeNames_whenFindByJPQL_thenEmployeeObject() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        String firstName = "isaac";
        String lastName = "home";
        // when -> action or the behaviour that we are going to test
        var emp = employeeRepo.findByJPQL(firstName, lastName);
        // then -> verify the output...
        assertThat(emp).isNotNull();
    }

    // JUnit test for custom query using JPQL  with index
    @DisplayName("Junit Test for findByJPQL with Named Param")
    @Test
    public void givenEmployeeNames_whenFindByNamedParam_thenEmployeeObject() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        String firstName = "isaac";
        String lastName = "home";
        // when -> action or the behaviour that we are going to test
        var emp = employeeRepo.findByJPQLNamesParams(firstName, lastName);
        // then -> verify the output...
        assertThat(emp).isNotNull();
    }

    @DisplayName("Junit Test for findByNativeSQL with Index Param")
    @Test
    public void givenEmployeeNames_whenFindByNativeSQL_thenEmployeeObject() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        String firstName = "isaac";
        String lastName = "home";
        // when -> action or the behaviour that we are going to test
        var emp = employeeRepo.findByNativeQueryWithIndexParams(firstName, lastName);
        // then -> verify the output...
        assertThat(emp).isNotNull();
    }

    @DisplayName("Junit Test for findByNativeSQL with Named Param")
    @Test
    public void givenEmployeeNames_whenFindByNativeQueryWithNamedParams_thenEmployeeObject() {
        // given -> precondition or setup
        employeeRepo.save(employee);
        String firstName = "isaac";
        String lastName = "home";
        // when -> action or the behaviour that we are going to test
        var emp = employeeRepo.findByNativeQueryWithNamedParams(firstName, lastName);
        // then -> verify the output...
        assertThat(emp).isNotNull();
    }

}