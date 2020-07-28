package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Database;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ozm
 * @date 2020/7/28 - 21:57
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    public List<Employee> getAllEmployees(@PathParam(value = "page") Integer page, @PathParam(value = "pageSize") Integer pageSize, @PathParam(value = "gender") String gender) {
        List<Employee> employees = Database.getEmployees();
        if (page != null && pageSize != null) {
            return employees.subList(page-1, pageSize);
        }

        if (gender != null) {
            return employees.stream().filter(employee -> employee.getGender() == "male").collect(Collectors.toList());
        }
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable int id) {
        List<Employee> employees = Database.getEmployees();
        for(Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @PostMapping
    public List<Employee> insertEmployees(@RequestBody Employee employee) {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        return employees;
    }

    @PutMapping
    public Employee updateEmployeeById(@RequestBody Employee employee){
        Employee oldEmployee = new Employee(1, "oocl", 22, "male", 5000);
        if (oldEmployee.getId() == employee.getId()) {
            oldEmployee.setName(employee.getName());
        }
        return oldEmployee;
    }

    @DeleteMapping
    public List<Employee> deleteAllEmployeesByEmployeeId(@PathParam(value = "id") int id) {
        List<Employee> employees = Database.getEmployees();
        return employees.stream().filter(employee -> employee.getId() != id).collect(Collectors.toList());

    }
}
