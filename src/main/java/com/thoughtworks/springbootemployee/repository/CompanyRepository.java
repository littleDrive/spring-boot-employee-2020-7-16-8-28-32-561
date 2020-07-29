package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {

    public List<Company> getCompanies() {
        return null;
    }

    public Company getCompanyById(int id) {
        return null;
    }

    public List<Employee> getEmployeesById(int companyId) {
        return null;
    }
}
