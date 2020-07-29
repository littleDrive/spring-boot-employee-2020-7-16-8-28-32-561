package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void should_return_companies_when_get_companies_given_no_parameter() {
        //given
        when(companyRepository.findAll()).thenReturn(
            asList(
                new Company(1, "OOCL", 1, asList(new Employee(1, "user1", 18, "male", 100.0))),
                new Company(2, "TW", 1, asList(new Employee(2, "user2", 20, "female", 200.0)))
            ));
        //when
        List<Company> companies = this.companyService.findAll();

        //then
        assertEquals(2, companies.size());
    }

    @Test
    void should_return_company_when_get_company_by_id_given_1() {
        //given
        int companyId = 1;
        when(companyRepository.findById(companyId)).
            thenReturn(Optional.of(
                new Company(1, "OOCL", 1, asList(new Employee(1, "user1", 18, "male", 100.0)))
            ));

        //when
        Company company = this.companyService.findById(companyId);

        //then
        assertEquals(companyId, company.getId());
    }

    @Test
    void should_return_employees_when_get_employees_by_company_id_given_1() {
        //given
        int companyId = 1;
        when(companyRepository.findById(companyId)).
            thenReturn(Optional.of(
                new Company(1, "OOCL", 1, asList(new Employee(1, "user1", 18, "male", 100.0)))
            ));

        //when
        List<Employee> employees = this.companyService.findEmployeesById(companyId);

        //then
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_company_when_add_company_given_company() {
        //given
        Company company = new Company(1, "OOCL", 1, asList(new Employee(1, "user1", 18, "male", 100.0)));
        when(companyRepository.save(company)).thenReturn(company);

        //when
        Company addCompany = companyService.add(company);

        //then
        assertEquals(1, addCompany.getId());
    }

    @Test
    void should_return_updated_company_when_update_company_given_id_and_company() {
        //given
        int companyId = 1;
        Company companyBefore = new Company(companyId, "TW", 2, new ArrayList<>());
        Company companyAfter = new Company(1, "OOCL", 1, asList(new Employee(1, "user1", 18, "male", 100.0)));
        when(companyRepository.findById(companyId))
            .thenReturn(Optional.of(companyBefore));
        when(companyRepository.save(companyBefore)).thenReturn(companyAfter);

        //when
        Company companyUpdated = this.companyService.update(companyId, companyAfter);

        //then
        assertEquals(companyAfter.getCompanyName(), companyUpdated.getCompanyName());
        assertEquals(companyAfter.getEmployeesNumber(), companyUpdated.getEmployeesNumber());
    }
}
