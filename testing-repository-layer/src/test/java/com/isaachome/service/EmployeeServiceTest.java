package com.isaachome.service;
import com.isaachome.model.Employee;
import com.isaachome.repository.EmployeeRepo;
import com.isaachome.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;


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

            // JUnit test for
        @DisplayName("JUnit test for saved Employee method which throws exception")
             @Test
             public  void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
                 // given -> precondition or setup
                given(employeeRepo.findByMail(employee.getMail()))
                        .willReturn(Optional.of(employee));
                // when
                Assertions.assertThatThrownBy(() -> employeeService.saveEmployee(employee));
                // then
                verify(employeeRepo,never()).save(any(Employee.class));
             }

      // JUnit test for
      @DisplayName("Junit test for get All Employee method")
      @Test
      public  void givenEmployeeList_whenGetAllEmployees_thenReturnEmployeesList(){
          // given -> precondition or setup
          given(employeeRepo.findAll()).willReturn(Arrays.asList(employee,employee,employee));
          // when -> action or the behaviour that we are going to test
            var employees = employeeService.getAllEmployees();
          // then -> verify the output...
          Assertions.assertThat(employees).isNotNull();
          Assertions.assertThat(employees.size()).isEqualTo(3);
      }

 // JUnit test for
     @DisplayName("JUnit test for get All employee method (negative scenario)")
     @Test
     public  void givenEmptyEmployeeList_whenGetAllEmployees_thenReturnEmptyList(){
         // given -> precondition or setup
         given(employeeRepo.findAll()).willReturn(Collections.emptyList());
         // when -> action or the behaviour that we are going to test
         var employees = employeeService.getAllEmployees();
         // then -> verify the output...
         Assertions.assertThat(employees).isEmpty();
         Assertions.assertThat(employees.size()).isEqualTo(0);
     }

 // JUnit test for
     @DisplayName("JUnit test for get employee by id")
     @Test
     public  void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee(){
         // given -> precondition or setup
         given(employeeRepo.findById(employee.getId()))
                 .willReturn(Optional.of(employee));
         // when -> action or the behaviour that we are going to test
         var foundEmployee = employeeService.getEmployeeById(employee.getId());
         // then -> verify the output...
         Assertions.assertThat(foundEmployee).isNotNull();
         Assertions.assertThat(foundEmployee.getId()).isEqualTo(employee.getId());
     }

    @DisplayName("JUnit test for get employee by id not found scenario")
    @Test
    public  void givenEmployeeId_whenGetEmployeeById_thenReturnNotEmployee(){
        // given -> precondition or setup
        given(employeeRepo.findById(1L))
                .willReturn(Optional.of(employee));
        // when -> action or the behaviour that we are going to test
        var foundEmployee = employeeService.getEmployeeById(1L);
        // then -> verify the output...
        Assertions.assertThat(foundEmployee).isNull();
    }


}
