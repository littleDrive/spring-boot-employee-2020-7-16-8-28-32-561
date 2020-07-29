package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_return_employees_when_get_employees_given_no_parameter() {
        //given
        when(employeeRepository.findAll()).thenReturn(
                asList(
                        new Employee(1,"user1", 18, "male", 1000.0)
                ));
        //when
        List<Employee> employees = this.employeeService.findAll();

        //then
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_employee_when_get_employee_by_id_given_1() {
        //given
        int employeeId = 1;
        when(employeeRepository.findById(employeeId)).
                thenReturn(Optional.of(
                        new Employee(1,"user1", 18, "male", 1000.0)
                ));

        //when
        Employee employee = this.employeeService.findById(employeeId);

        //then
        assertEquals(employeeId, employee.getId());
    }

}
