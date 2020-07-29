package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
