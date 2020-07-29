package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company findById(int companyId) {
        return this.companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> findEmployeesById(int companyId) {
        Company company = this.companyRepository.findById(companyId).orElse(null);
        return company == null ? null : company.getEmployees();
    }
}
