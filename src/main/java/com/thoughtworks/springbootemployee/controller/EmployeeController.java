package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Database;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author ozm
 * @date 2020/7/28 - 21:57
 */

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @GetMapping
    public List<Employee> getAllEmployees() {
        List<Employee> employees = Database.getEmployees();
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
}
