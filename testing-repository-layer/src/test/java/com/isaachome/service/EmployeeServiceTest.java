package com.isaachome.service;

import com.isaachome.model.Employee;
import com.isaachome.repository.EmployeeRepo;
import com.isaachome.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepo employeeRepo;

//    private EmployeeService employeeService;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
//        employeeRepo = Mockito.mock(EmployeeRepo.class);
//        employeeService = new EmployeeServiceImpl(employeeRepo);
        employee =  Employee.builder()
                .id(1)
                .firstName("mercy")
                .lastName("home")
                .mail("mercyhome@gmail.com")
                .build();
    }

        // JUnit test for saveEmployee method
    @DisplayName("Junit test for savedEmployee method")
        @Test
        public  void givenEmployeeObject_whenSaveEmployee_thenReturnSavedEmployee() {
            // given -> precondition or setup
             given(employeeRepo.findByMail(employee.getMail()))
                     .willReturn(Optional.empty());

             given(employeeRepo.save(employee))
                     .willReturn(employee);
            // when -> action or the behavior that we are going to test
            var savedEmployee = employeeService.saveEmployee(employee);
            // then -> verify the output...
            Assertions.assertThat(savedEmployee).isNotNull();
        }


}
