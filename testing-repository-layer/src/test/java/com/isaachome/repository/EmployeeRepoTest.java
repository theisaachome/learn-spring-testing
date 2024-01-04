package com.isaachome.repository;

import com.isaachome.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EmployeeRepoTest {
    @Autowired
    private EmployeeRepo employeeRepo;

    // Junit test for save Employee operation
    @DisplayName("Junit test for save Employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){
        // given -> precondition or setup
        var employee = Employee.builder()
                .firstName("isaac")
                .lastName("home")
                .mail("isaachome@gmail.com")
                .build();

        // when -> action or the behaviour that we are going to test
        var savedEmployee = employeeRepo.save(employee);

        // then -> verify the output...
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

}