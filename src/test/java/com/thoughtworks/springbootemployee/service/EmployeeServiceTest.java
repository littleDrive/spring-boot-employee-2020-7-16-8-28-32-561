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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    void should_return_employees_when_get_employees_given_gender() {
        //given
        String gender = "male";
        when(employeeRepository.findAllByGender(gender)).
                thenReturn(
                        asList(new Employee(1,"user1", 18, "male", 1000.0))
                );
        //when
        List<Employee> employees = employeeService.findAllByGender(gender);

        //then
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        //given
        Employee employee = new Employee(1,"user1", 18, "male", 1000.0);
        when(employeeRepository.save(employee)).thenReturn(employee);

        //when
        Employee addEmployee = employeeService.add(employee);

        //then
        assertEquals(1, addEmployee.getId());
    }

    @Test
    void should_return_updated_employee_when_update_employee_given_id_and_company() {
        //given
        int employeeId = 1;
        Employee employeeBefore = new Employee(1,"user1", 18, "male", 1000.0);
        Employee employeeAfter = new Employee(2,"user2", 18, "female", 2000.0);
        when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.of(employeeBefore));
        when(employeeRepository.save(employeeBefore)).thenReturn(employeeAfter);

        //when
        Employee employeeUpdated = this.employeeService.update(employeeId, employeeAfter);

        //then
        assertEquals(employeeAfter.getName(), employeeUpdated.getName());
        assertEquals(employeeAfter.getAge(), employeeUpdated.getAge());
    }

    @Test
    void should_return_nothing_when_delete_employee_given_employeeId() {
        //given
        Employee employee = new Employee(1, "mandy", 18, "female", 66666.0);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        //when
        employeeService.deleteEmployeeById(1);
        //then
        verify(employeeRepository,times(1)).deleteById(1);
    }
}
