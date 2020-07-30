package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    public List<Employee> findAll(int page, int pageSize) {
        return this.employeeRepository.findAll(PageRequest.of(page, pageSize)).toList();
    }

    public Employee add(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee findById(int employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> findAllByGender(String gender) {
        return this.employeeRepository.findAllByGender(gender);
    }

    public Employee update(int employeeId, Employee employe) {
        Employee employeeUpdated = this.employeeRepository.findById(employeeId).orElse(null);
        if (employeeUpdated == null) {
            return null;
        }
        employeeUpdated.setName(employe.getName());
        employeeUpdated.setAge(employe.getAge());
        employeeUpdated.setGender(employe.getGender());
        employeeUpdated.setSalary(employe.getSalary());
        return this.employeeRepository.save(employeeUpdated);
    }

    public void deleteEmployeeById(int i) {
        employeeRepository.deleteById(i);
    }
}
