package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @After
    void deleteData() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_employees_when_get_employees_given_nothing() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee = new Employee(1,"user1", 18, "male", 1000.0, savedCompany.getId());
        employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(employee.getId()))
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()));
    }

    @Test
    void should_return_employees_when_get_employees_given_id() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee1 = new Employee(1,"user1", 18, "male", 1000.0, savedCompany.getId());
        Employee employee2 = new Employee(2,"user2", 19, "female", 9000.0, savedCompany.getId());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when
        mockMvc.perform(get("/employees/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee2.getId()))
                .andExpect(jsonPath("$.name").value(employee2.getName()))
                .andExpect(jsonPath("$.gender").value(employee2.getGender()))
                .andExpect(jsonPath("$.salary").value(employee2.getSalary()))
                .andExpect(jsonPath("$.age").value(employee2.getAge()));
    }

    @Test
    void should_return_employees_when_get_employees_given_page_and_pageSize() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee1 = new Employee(1,"user1", 25, "male", 8000.0, savedCompany.getId());
        Employee employee2 = new Employee(2,"user2", 22, "female", 9000.0, savedCompany.getId());
        Employee employee3 = new Employee(3,"user3", 21, "female", 9000.0, savedCompany.getId());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when
        mockMvc.perform(get("/employees?page=1&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee3.getId()))
                .andExpect(jsonPath("$[0].name").value(employee3.getName()))
                .andExpect(jsonPath("$[0].gender").value(employee3.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee3.getSalary()))
                .andExpect(jsonPath("$[0].age").value(employee3.getAge()));
    }

    @Test
    void should_return_employees_when_get_employees_given_gender() throws Exception{
        //given
        Company company = new Company(1, "OOCL", 10000, null);
        Company savedCompany = companyRepository.save(company);

        Employee employee1 = new Employee(1,"user1", 25, "male", 8000.0, savedCompany.getId());
        Employee employee2 = new Employee(2,"user2", 22, "female", 9000.0, savedCompany.getId());
        Employee employee3 = new Employee(3,"user3", 21, "female", 9000.0, savedCompany.getId());
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        //when
        mockMvc.perform(get("/employees?gender=female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id").value(employee3.getId()))
                .andExpect(jsonPath("$[1].name").value(employee3.getName()))
                .andExpect(jsonPath("$[1].gender").value(employee3.getGender()))
                .andExpect(jsonPath("$[1].salary").value(employee3.getSalary()))
                .andExpect(jsonPath("$[1].age").value(employee3.getAge()));
    }
}
