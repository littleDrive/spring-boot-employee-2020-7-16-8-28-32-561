package com.thoughtworks.springbootemployee.Mapper;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.vo.RequestCompany;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public static Company getCompanyByRequestCompany(RequestCompany requestCompany) {

        Company company = new Company();
        company.setId(requestCompany.getId());
        company.setCompanyName(requestCompany.getCompanyName());
        company.setEmployeesNumber(requestCompany.getEmployeesNumber());
        return company;

    }
}
