package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanies(Integer page, Integer pageSize) {
        return null;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Company getCompanyById(@PathVariable int id) {
        return null;
    }

    @GetMapping("/{id}/employees")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable int id) {
        return null;
    }

    @PostMapping
    public List<Company> insertCompany(@RequestBody Company company) {
        return null;
    }

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable int id, @RequestBody Company company){
        return null;
    }

    @DeleteMapping("/{id}")
    public Company deleteAllEmployeesByCompanyId(@PathVariable int id) {
        return null;
    }
}
