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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void should_return_companies_when_get_companies() {
        //given
        when(companyRepository.getCompanies()).thenReturn(
                new ArrayList<>(Arrays.asList(new Company(1, "aaa", 2, new ArrayList<>()),
                        new Company(2, "bbb", 3, new ArrayList<>()))
                ));

        //when
        List<Company> companies = companyService.getCompanies();

        //then
        assertNotNull(companies);

    }

    @Test
    void should_return_company_when_get_company_by_id_given_1() {
        //given
        int companyId = 1;
        when(companyRepository.getCompanyById(companyId)).thenReturn(new Company(1, "aaa", 2, new ArrayList<>()));

        //when
        Company company = companyService.getCompanyById(companyId);

        //then
        assertEquals(companyId, company.getId());
    }

    @Test
    void should_return_employees_when_get_employees_by_company_id_given_1() {
        //given
        int companyId = 1;
        when(companyRepository.getEmployeesById(companyId)).thenReturn(
                new ArrayList<>(Collections.singletonList(new Employee(1, "zzz", 18, "male", 10))));
        //when
        List<Employee> employees = companyService.getEmployeesById(companyId);

        //then
        assertEquals(1, employees.size());
        assertEquals(1, employees.get(0).getId());
    }
}
