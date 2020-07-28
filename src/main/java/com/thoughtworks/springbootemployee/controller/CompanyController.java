package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Database;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping
    public List<Company> getCompanies() {
        List<Company> companies = Database.getCompanies();
        return companies;
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        List<Company> companies = Database.getCompanies();
        for (Company company : companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesFromCompanyId(@PathVariable int id) {
        List<Company> companies = Database.getCompanies();
        for (Company company : companies) {
            if (company.getId() == id) {
                return company.getEmployees();
            }
        }
        return null;
    }


}
