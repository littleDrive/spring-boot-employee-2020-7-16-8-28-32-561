package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return this.companyRepository.getCompanies();
    }

    public Company getCompanyById(int companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesById(int companyId) {
        return null;
    }
}
