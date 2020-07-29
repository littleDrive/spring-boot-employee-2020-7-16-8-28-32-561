package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Database;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping
    public List<Company> getCompanies(@PathParam(value = "page") Integer page, @PathParam(value = "pageSize") Integer pageSize) {
        List<Company> companies = Database.getCompanies();
        if (page != null && pageSize != null) {
            return companies.subList(page-1, pageSize);
        }
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
//        companies.stream().filter(company -> company.getId() == id).collect(Collectors.toList());
        return null;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable int id) {
        List<Company> companies = Database.getCompanies();
        for (Company company : companies) {
            if (company.getId() == id) {
                return company.getEmployees();
            }
        }
        return null;
    }

    @PostMapping
    public List<Company> insertCompany(@RequestBody Company company) {
        List<Company> companies = new ArrayList<>();
        companies.stream().map(Company::getId).collect(Collectors.toList());

        companies.add(company);
        return companies;
    }

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable int id, @RequestBody Company company){
//        todo
        Company oldcompany = new Company(1, "blibili", 10, new ArrayList<>());
        if (oldcompany.getId() == id) {
            oldcompany.setCompanyName(company.getCompanyName());
        }

        return oldcompany;
    }

    @DeleteMapping
    public Company deleteAllEmployeesByCompanyId(@PathParam(value = "id") int id) {
        List<Company> companies = Database.getCompanies();
        for (Company company : companies) {
            if (company.getId() == id) {
                company.setEmployees(new ArrayList<>());
                return company;
            }
        }

        return null;
    }
}
