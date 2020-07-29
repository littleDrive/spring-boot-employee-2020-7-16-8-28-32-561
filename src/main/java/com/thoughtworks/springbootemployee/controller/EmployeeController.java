package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(Integer page, Integer pageSize, String gender) {
        return null;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeByID(@PathVariable int id) {
        return null;
    }

    @PostMapping
    public Employee insertEmployees(@RequestBody Employee employee) {
        return null;
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable int id, @RequestBody Employee employee){
        return null;
    }

    @DeleteMapping("/{id}")
    public List<Employee> deleteAllEmployeesById(@PathVariable int id) {
        return null;
    }
}
